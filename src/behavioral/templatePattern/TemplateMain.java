package behavioral.templatePattern;

import behavioral.templatePattern.templatePkg.BaseGameLoader;
import behavioral.templatePattern.templatePkg.DiabloLoader;
import behavioral.templatePattern.templatePkg.WorldOfWarcraftLoader;

public class TemplateMain {

    public static void main(String[] args) {

        System.out.println();

        BaseGameLoader wowLoader = new WorldOfWarcraftLoader();
        wowLoader.load();
        System.out.println();

        System.out.println("==========================================");
        System.out.println();

        BaseGameLoader diabloLoader = new DiabloLoader();
        diabloLoader.load();

    }
}
