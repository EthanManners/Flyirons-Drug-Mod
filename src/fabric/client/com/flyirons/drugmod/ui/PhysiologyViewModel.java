package com.flyirons.drugmod.ui;

public final class PhysiologyViewModel {
    private static String focusDrug = "none";
    private static float potency;
    private static float tolerance;
    private static float addiction;
    private static int withdrawalTicks;
    private static float overdoseRisk;

    private PhysiologyViewModel() {}

    public static void update(String focus, float potencyPercent, float toleranceValue, float addictionValue, int withdrawal, float overdose) {
        focusDrug = focus;
        potency = potencyPercent;
        tolerance = toleranceValue;
        addiction = addictionValue;
        withdrawalTicks = withdrawal;
        overdoseRisk = overdose;
    }

    public static String focusDrug() { return focusDrug; }
    public static float potency() { return potency; }
    public static float tolerance() { return tolerance; }
    public static float addiction() { return addiction; }
    public static int withdrawalTicks() { return withdrawalTicks; }
    public static float overdoseRisk() { return overdoseRisk; }
}
