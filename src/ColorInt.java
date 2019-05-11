/**
 * Color itself is a primitive type. In order to use a Hash Map, need to have a
 * separate class to hold the R, G, B value for each color and map it to
 * corresponding names.
 *
 */

public class ColorInt {
    private String colorName;
    private int rValue;
    private int gValue;
    private int bValue;

    public ColorInt(String cName, int rV, int gV, int bV) {
	colorName = cName;
	rValue = rV;
	gValue = gV;
	bValue = bV;
    }

    public String getColorName() {
	return colorName;
    }

    public void setColorName(String colorName) {
	this.colorName = colorName;
    }

    public int getrValue() {
	return rValue;
    }

    public void setrValue(int rValue) {
	this.rValue = rValue;
    }

    public int getgValue() {
	return gValue;
    }

    public void setgValue(int gValue) {
	this.gValue = gValue;
    }

    public int getbValue() {
	return bValue;
    }

    public void setbValue(int bValue) {
	this.bValue = bValue;
    }

}
