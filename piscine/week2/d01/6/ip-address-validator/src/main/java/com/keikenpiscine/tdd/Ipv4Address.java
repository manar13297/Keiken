package com.keikenpiscine.tdd;

public class Ipv4Address {
    public boolean ValidateIpv4Address(String ip) {
        String[] parts = ip.split("\\.");

        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            int intPart;
            try {
                intPart = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                return false;
            }

            if (intPart < 0 || intPart > 255) {
                return false;
            }
        }

        return !ip.endsWith(".0") && !ip.endsWith(".255");
    }
}
