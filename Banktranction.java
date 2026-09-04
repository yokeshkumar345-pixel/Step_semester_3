public class Banktranction {
    static String normalizeReference(String raw){
        raw = raw.trim();

        return raw.substring(0, 3).toUpperCase()
             + raw.substring(3);
    }

    static String validateAndFormat(String ref){
        if(ref.length() != 14)
            return "Invalid: wrong length";

        for(int i = 0; i < 3; i++){
            if(!Character.isLetter(ref.charAt(i)))
                return "Invalid: bank code must be 3 letters";
        }

        for(int i = 3; i < 14; i++){
            if(!Character.isDigit(ref.charAt(i)))
                return "Invalid: body must contain only digits";
        }

        String bank = ref.substring(0, 3);
        String date = ref.substring(3, 9);
        String seq = ref.substring(9);

        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(bank);
        result.append("] DATE: ");
        result.append(date.substring(0, 2));
        result.append("/");
        result.append(date.substring(2, 4));
        result.append("/");
        result.append(date.substring(4));
        result.append(" | SEQ: ");
        result.append(seq);

        return result.toString();
    }

    public static void main(String[] args){
        String ref = normalizeReference(" hdf03022600042 ");

        System.out.println(validateAndFormat(ref));

        String ref2 = normalizeReference("12F03022600042");

        System.out.println(validateAndFormat(ref2));
    }
}