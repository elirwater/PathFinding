import controller.BasicBotController;
import controller.BasicPathFindingModelController;
import controller.ControllerInterface;
import controller.UserBotController;
import model.BasicPathFindingModel;
import model.Pair;

public class main {



    public static void main(String[] args) throws InterruptedException {

        String bot = "";
        String map = "";
        String controllerType = "";
        int gridSizeX = 0;  //width
        int gridSizeY = 0;  //height


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
            if (args[i].equalsIgnoreCase("-gridSize")) {
                try {
                    gridSizeX = Integer.parseInt(args[i + 1]);
                    gridSizeY = Integer.parseInt(args[i + 2]);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Must provide valid argument for -blockSize");
                }
            }
        }

        BasicPathFindingModel m = new BasicPathFindingModel(new Pair(gridSizeX, gridSizeY), map, bot);

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
