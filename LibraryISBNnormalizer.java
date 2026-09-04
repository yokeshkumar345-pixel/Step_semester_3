public class LibraryISBNnormalizer{
    static String normalizeCode(String raw){
        raw = raw.trim();

        return raw.substring(0, 3).toUpperCase()
             + raw.substring(3);
    }

    static String validateAndFormat(String code){
        if(code.length() != 13)
            return "Invalid: wrong length";

        for(int i = 0; i < 3; i++)
        {
            if(!Character.isLetter(code.charAt(i)))
                return "Invalid: publisher code must be 3 letters";
        }

        for(int i = 3; i < 13; i++)
        {
            if(!Character.isDigit(code.charAt(i)))
                return "Invalid: body must contain only digits";
        }

        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);

        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(publisher);
        result.append("] YEAR: ");
        result.append(year);
        result.append(" | CATALOG: ");
        result.append(catalog);

        return result.toString();
    }

    public static void main(String[] args){
        String code = normalizeCode(" pen2026004251 ");

        System.out.println(validateAndFormat(code));

        String code2 = normalizeCode("12N2026004251");

        System.out.println(validateAndFormat(code2));
    }
}