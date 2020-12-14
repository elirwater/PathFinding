import controller.BasicBotController;
import controller.BasicPathFindingModelController;
import controller.ControllerInterface;
import controller.UserBotController;
import model.BasicPathFindingModel;

public class main {



    public static void main(String[] args) throws InterruptedException {

        String bot = "";
        String map = "";
        String controllerType = "";
        int blockSize = 0;
        int numBlocks = 0;


        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-bot")) {
                try {
                    bot = args[i + 1];
                } catch (Exception e) {
                    throw new IllegalArgumentException("Must provide an argument after bot");
                }
            }
            if (args[i].equalsIgnoreCase("-map")) {
                try {
                    map = args[i + 1];
                } catch (Exception e) {
                    throw new IllegalArgumentException("Must provide an argument after map");
                }
            }
            if (args[i].equalsIgnoreCase("-controller")) {
                try {
                    controllerType = args[i + 1];
                } catch (Exception e) {
                    throw new IllegalArgumentException("Must provide an argument controller");
                }
            }
            if (args[i].equalsIgnoreCase("-blockSize")) {
                try {
                    blockSize = Integer.parseInt(args[i + 1]);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Must provide valid argument for -blockSize");
                }
            }
            if (args[i].equalsIgnoreCase("-numBlocks")) {
                try {
                    numBlocks = Integer.parseInt(args[i + 1]);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Must provide valid argument for -numBlocks");
                }
            }
        }

        BasicPathFindingModel m = new BasicPathFindingModel(blockSize, numBlocks, map, bot);

        ControllerInterface c;

        //Should be in a separate controller factory class

        switch (controllerType) {
            case "BasicPathFindingModelController":
                c = new BasicPathFindingModelController(m);
                break;
            case "UserBotController":
                c = new UserBotController(m);
                break;
            case "BasicBotController":
                c = new BasicBotController(m);
                break;
            default:
                throw new IllegalArgumentException("Not a valid controller type");
        }

        c.go();



        //BasicPathFindingModel m = new BasicPathFindingModel(600, 5625, "DepthFirstSearch", "BasicBot");

       //BasicPathFindingModelController c = new BasicPathFindingModelController(m);
        //c.go();


       // BasicPathFindingModel m = new BasicPathFindingModel(600, 5625, "DepthFirstSearch", "UserBot");


       // UserBotController c = new UserBotController(m);
        //c.go();

    }
}
