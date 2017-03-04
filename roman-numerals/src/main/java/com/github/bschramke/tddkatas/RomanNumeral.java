package com.github.bschramke.tddkatas;

public enum RomanNumeral {
        M(1000),CM(900),D(500),CD(400),C(100),XC(90),L(50),XL(40),X(10),IX(9),V(5),IV(4),I(1);

        private final int weight;

        RomanNumeral(int weight) {
            this.weight = weight;
        }

        public int weight() {
            return weight;
        }

        public RomanNumeral valueOf(char ch){
            return RomanNumeral.valueOf(String.valueOf(ch));
        }

        public static boolean isSymbol(final String numeral) {
            try {
                RomanNumeral.valueOf(numeral);
            }catch (IllegalArgumentException e) {
                return false;
            }

            return true;
        }

        public static boolean isSubstractionAllowed(final char first, final char second) {
            boolean allowed = false;

            switch (first) {
                case 'I':
                    allowed = second == 'V' || second == 'X';
                    break;
                case 'X':
                    allowed = second == 'L' || second == 'C';
                    break;
                case 'C':
                    allowed = second == 'D' || second == 'M';
                    break;
            }

            return allowed;
        }
    }
