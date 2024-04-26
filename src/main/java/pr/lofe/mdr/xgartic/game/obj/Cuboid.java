package pr.lofe.mdr.xgartic.game.obj;

public record Cuboid(Point first, Point second) {

    public boolean isIn(Point point) {
        double minX = Math.min(first.x(), second.x()), minY = Math.min(first.y(), second.y()), minZ = Math.min(first.z(), second.z());
        double maxX = Math.max(first.x(), second.x()), maxY = Math.max(first.y(), second.y()), maxZ = Math.max(first.z(), second.z());
        return minX < point.x() && point.x() < maxX &&
                minY < point.y() && point.y() < maxY &&
                minZ < point.z() && point.z() < maxZ;
    }

    @Override
    public String toString() {return String.format("cuboid::%s::%s", first.toString(), second.toString());}

    public static Cuboid fromString(String string) {
        String[] unboxed = string.split("::");
        if (unboxed.length == 3 && unboxed[0].equals("cuboid")) {
            return new Cuboid(Point.fromString(unboxed[1]), Point.fromString(unboxed[2]));
        }
        return null;
    }

}
