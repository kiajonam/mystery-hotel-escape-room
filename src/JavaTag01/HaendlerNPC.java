package JavaTag01;

/**
 * Exercise: Mittel 3 - Ein Spielobjekt mit zwei Fähigkeiten.
 *
 * The NPC can be interacted with and can also trade.
 */
interface Interagierbar {
    String interagieren();
}

interface Handelbar {
    String handeln();
}

public class HaendlerNPC implements Interagierbar, Handelbar {
    private String name;

    public HaendlerNPC(String name) {
        this.name = name;
    }

    @Override
    public String interagieren() {
        return name + ": Willkommen, Reisender!";
    }

    @Override
    public String handeln() {
        return name + " oeffnet den Shop.";
    }

    public static void main(String[] args) {
        HaendlerNPC npc = new HaendlerNPC("Rufus");

        Interagierbar dialog = npc;
        Handelbar shop = npc;

        System.out.println(dialog.interagieren());
        System.out.println(shop.handeln());
    }
}
