package org.usfirst.frc.team4795.robot;

import java.util.*;
import edu.wpi.first.wpilibj.*;

public class Dial {

    private final DigitalInput dialBit0;
    private final DigitalInput dialBit1;
    private final DigitalInput dialBit2;
    private BitSet dialNumber;

    public Dial(int bit0channel, int bit1channel, int bit2channel) {
        dialBit0 = new DigitalInput(bit0channel);
        dialBit1 = new DigitalInput(bit1channel);
        dialBit2 = new DigitalInput(bit2channel);
        dialNumber = new BitSet(3);

    }

    public int getDialPosition() {
        dialNumber.set(0, dialBit0.get());
        dialNumber.set(1, dialBit1.get());
        dialNumber.set(2, dialBit2.get());

        return (int) convertBitSet(dialNumber);
    }

    public static long convertBitSet(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
    }
}
