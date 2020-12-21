package com.melonsandwich.calculator;

import java.util.HashMap;
import java.util.Map;

public enum Operator
{
    ADD("+") {
        @Override
        public double count(double x, double y) {
            return x + y;
        }
    },

    SUBSTRACT("-") {
        @Override
        public double count(double x, double y) {
            return x - y;
        }
    },

    MULTIPLY("*") {
        @Override
        public double count(double x, double y) {
            return x * y;
        }
    },

    DIVIDE("/") {
        @Override
        public double count(double x, double y) {
            return x / y;
        }
    };

    public abstract double count(double x, double y);

    private final String text;
    private static final Map<String, Operator> lookup = new HashMap<String, Operator>();

    Operator(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    static
    {
        for (Operator operator : Operator.values())
        {
            lookup.put(operator.toString(), operator);
        }
    }

    public static Operator get(String value)
    {
        return lookup.get(value);
    }
}
