package pr.lofe.mdr.xgartic.game.obj;

public record Room(Point spawn, Cuboid zone) {

    @Override
    public String toString() {return String.format("room:::%s:::%s", spawn.toString(), zone.toString());}

    public static Room fromString(String string) {
        String[] unboxed = string.split(":::");
        if (unboxed.length == 3 && unboxed[0].equals("cuboid")) {
            return new Room(Point.fromString(unboxed[1]), Cuboid.fromString(unboxed[2]));
        }
        return null;
    }


}
