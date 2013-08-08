
public class Main {

    public static void main(String ...args) {
        if(args.length != 1) {
            System.err.println("Only needed argument is a single character");
        } else {
            String character = args[0];
            System.out.println("Unicode: "+CharacterRepresentation.asUnicode(character));
            System.out.println("Decimal: "+CharacterRepresentation.asDecimal(character));
            System.out.println("Hexadecimal: "+CharacterRepresentation.asHexadecimal(character));
        }


    }
}
