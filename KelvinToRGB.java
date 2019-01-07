public class KelvinToRGB {
    private static int temperatureLocal;

    public static String getRGB(int temperature, int brightness) {
        temperatureLocal = constrain(temperature, 0, 65500);
        int Brightness = constrain(brightness, 0, 100);
        int Red = map(Brightness, 0, 100, 0, calculateRed());
        int Green = map(Brightness, 0, 100, 0, calculateGreen());
        int Blue = map(Brightness, 0, 100, 0, calculateBlue());

        String sR = Integer.toHexString(Red);
        String sG = Integer.toHexString(Green);
        String sB = Integer.toHexString(Blue);
        if (sR.length() == 1)
            sR = "0" + sR;
        if (sG.length() == 1)
            sG = "0" + sG;
        if (sB.length() == 1)
            sB = "0" + sB;
        return "#" + sR + sG + sB;
    }

    private static int constrain(int x, int a, int b) {
        if (x >= a && x <= b)
            return x;
        if (x < a)
            return a;
        if (x > b)
            return b;
        return 0;
    }

    private static int map(int value, int oldRangeStart, int oldRangeEnd, int newRangeStart, int newRangeEnd) {
        float perc = value / ((oldRangeEnd - oldRangeStart) / 100);
        return Math.round(newRangeStart + ((float) (newRangeEnd - newRangeStart) / 100 * perc));
    }

    private static int calculateRed() {
        double red = 255;
        int temperature = temperatureLocal / 100;
        if (temperature > 66) {
            red = temperature - 60;
            red = 329.698727466 * Math.pow(red, -0.1332047592);
        }
        return constrain(Math.round((float) red), 0, 255);
    }

    private static int calculateGreen() {
        double green;
        int temperature = temperatureLocal / 100;
        if (temperature <= 66) {
            green = temperature;
            green = (99.4708025861 * Math.log(green)) - 161.1195681661;
        } else {
            green = temperature - 60;
            green = 288.1221695283 * Math.pow(green, -0.0755148492);
        }
        return constrain(Math.round((float) green), 0, 255);
    }

    private static int calculateBlue() {
        double blue = 255;
        int temperature = temperatureLocal / 100;
        if (temperature < 65) {
            if (temperature <= 19) {
                blue = 0;
            } else {
                blue = temperature - 10;
                blue = (138.5177312231 * Math.log(blue)) - 305.0447927307;
            }
        }
        return constrain(Math.round((float) blue), 0, 255);
    }

}
