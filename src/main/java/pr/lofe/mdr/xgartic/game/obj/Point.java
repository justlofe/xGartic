package pr.lofe.mdr.xgartic.game.obj;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.util.StringUtil;

@SuppressWarnings("unused")
public class Point {

    private double x, y, z;
    private float pitch, yaw;

    public Point(double x, double y, double z, float pitch, float yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = 0;
        this.yaw = 0;
    }

    public double x() {return x;}
    public double y() {return y;}
    public double z() {return z;}
    public float pitch() {return pitch;}
    public float yaw() {return yaw;}

    public void x(double x) {this.x = x;}
    public void y(double y) {this.y = y;}
    public void z(double z) {this.z = z;}
    public void pitch(float pitch) {this.pitch = pitch;}
    public void yaw(float yaw) {this.yaw = yaw;}

    @Override public String toString() {return String.format("point:%f:%f:%f:%f:%f", x, y, z, pitch, yaw);}

    public static Point fromString(@NotNull String string) {
        String[] unboxed = string.split(":");
        if(unboxed.length == 6 && unboxed[0].equals("point")) {
            boolean allNum = true;
            for(int i = 1; i < 6; i++) {
                if(!StringUtil.isNumeric(unboxed[i])) return null;
            }
            return new Point(
                    Double.parseDouble(unboxed[1]),
                    Double.parseDouble(unboxed[2]),
                    Double.parseDouble(unboxed[3]),
                    Float.parseFloat(unboxed[4]),
                    Float.parseFloat(unboxed[5])
            );
        }
        return null;
    }

    public static Point fromLocation(@NotNull Location loc) {
        return new Point(loc.x(), loc.y(), loc.z(), loc.getPitch(), loc.getYaw());
    }

    public static Location fromPoint(@NotNull Point point, @NotNull World world) {
        return new Location(world, point.x, point.y, point.z, point.pitch, point.yaw);
    }

}
