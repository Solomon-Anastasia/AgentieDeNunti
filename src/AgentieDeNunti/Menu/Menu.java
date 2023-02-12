package AgentieDeNunti.Menu;

/**
 * Class only used to show the menu
 */
public class Menu {
    /**
     * Showing menu.
     */
    public static void getMenu() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("| 0 ~ Exit                                                    |");
        System.out.println("| 1 ~ Add event                                               |");
        System.out.println("| 2 ~ Delete event by hall                                    |");
        System.out.println("| 3 ~ Get halls from a country entered from the keyboard      |");
        System.out.println("| 4 ~ Get events in calendar order                            |");
        System.out.println("| 5 ~ Get halls with max guests over 25 and price up to 150 $ |");
        System.out.println("| 6 ~ Editing the event date                                  |");
        System.out.println("| 7 ~ Sorting events in ascending order by price              |");
        System.out.println("| 8 ~ Show only (1 - weddings || 2 - splice)                  |");
        System.out.println("| 9 ~ Show available halls                                    |");
        System.out.println("| 10 ~ Show all events                                        |");
        System.out.println("---------------------------------------------------------------");
    }

    /**
     * For clearing the screen to increase readability.
     */
    public static void cls() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
