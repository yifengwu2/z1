package com.l;

public class Syn extends Thread {
    final StringBuffer letter;

    public Syn(StringBuffer letter) {
        this.letter = letter;
    }

    @Override
    public void run() {
        synchronized (letter) {
            for (int i = 0; i <= 100; ++i) {
                System.out.print(letter);
            }
            System.out.println();
            char temp = letter.charAt(0);
            ++temp;
            letter.setCharAt(0, temp);
        }
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("A");
        new Syn(sb).start();
        new Syn(sb).start();
        new Syn(sb).start();

    }

}
