package day5;

public class BitCharStuffing {

    public static String getBitStuffedSequence(String msg) {
        int len = msg.length();
        StringBuilder bitStuffedSeq = new StringBuilder();

        int oneCounter = 0;

        for(int i = 0; i < len; i++) {
            if(msg.charAt(i) == '1') {
                oneCounter++;
            } else {
                oneCounter = 0;
            }

            bitStuffedSeq.append(msg.charAt(i));

            if(oneCounter == 5) {
                bitStuffedSeq.append("0");
                oneCounter = 0;
            }
        }

        return bitStuffedSeq.toString();
    }


    public static String getCharStuffedSequence(String msg) {
        String [] msgArr = msg.split(" ");
        StringBuilder charStuffedSeq = new StringBuilder();

        for(String itm: msgArr) {
            if(itm.equals("FLAG")) {
                charStuffedSeq.append("ESCFLAG ");
            } else {
                charStuffedSeq.append(itm).append(" ");
            }
        }
        return charStuffedSeq.toString();
    }

    public static void main(String[] args) {
        String bitSeq = "111111101111110111111";
        String charMsg = "Hello FLAG my name is Anurup FLAG Networking lab FLAG";

        System.out.println("Bit Seq before bit stuffing");
        System.out.println(bitSeq);
        System.out.println("Bit Seq after bit stuffing");
        System.out.println(getBitStuffedSequence(bitSeq));


        System.out.println("Message Seq before bit stuffing");
        System.out.println(charMsg);
        System.out.println("Message Seq after bit stuffing");
        System.out.println(getCharStuffedSequence(charMsg));
    }

}
