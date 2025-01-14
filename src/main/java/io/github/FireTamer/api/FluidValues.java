package io.github.FireTamer.api;

public enum FluidValues {

    OXYGEN("oxygen", 0xFFFFFFFF, 0, 90.19F, -1_141),
    HYDROGEN("hydrogen", 0xFF6CE2FF, 0, 20.28F, -70.85F),
    LIQUID_HYDROGEN("liquid_hydrogen", 0xFF898FFF, 0, 20.28F, 70.85F),
    NAMEK_WATER("namek_water", 0xFF197302, 0, 90, 70F);

    private final String name;
    private final int color;
    private final int luminosity;
    private final float temperature;
    private final float density;

    /**
     * @param name        The name of the chemical
     * @param color       Visual color in ARGB format
     * @param luminosity  Luminosity
     * @param temperature Temperature in Kelvin that the chemical exists as a liquid
     * @param density     Density as a liquid in kg/m^3
     */
    FluidValues(String name, int color, int luminosity, float temperature, float density) {
        this.name = name;
        this.color = color;
        this.luminosity = luminosity;
        this.temperature = temperature;
        this.density = density;
    }

    public String getName() { return name; }

    public int getColor() { return color; }

    public float getTemperature() { return temperature; }

    public float getDensity() { return density; }

    public int getLuminosity() { return luminosity; }

}
