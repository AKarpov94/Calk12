
import java.util.Scanner;
public class Calk12 {

        public static boolean isArabicFigure(String input) //функция проверяет строку на вход корректная
        {
            String s = input.trim(); //убирает пробелы

            try // исключения
            {
                Integer.parseInt(s); // исключение + ничего не делаем , если
                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public static boolean isRomanFigure(String input)
        {
            String s = input.trim();
            if (s.isEmpty())
                return false;

            s = s.replaceAll("I", "");
            s = s.replaceAll("V", "");
            s = s.replaceAll("X", "");

            return s.isEmpty();
        }

        public static int parseString(String input, boolean arabic) throws Exception //
        {
            String s = input.trim();
            int i;

            if (arabic)
                i = Integer.parseInt(s);
            else
            {
                if (s.equals("I"))
                    i = 1;
                else if (s.equals("II"))
                    i = 2;
                else if (s.equals("III"))
                    i = 3;
                else if (s.equals("IV"))
                    i = 4;
                else if (s.equals("V"))
                    i = 5;
                else if (s.equals("VI"))
                    i = 6;
                else if (s.equals("VII"))
                    i = 7;
                else if (s.equals("VIII"))
                    i = 8;
                else if (s.equals("IX"))
                    i = 9;
                else if (s.equals("X"))
                    i = 10;
                else throw new Exception(); //новое исключение
            }

            return i;
        }

    public static String toRomanView(int a)
    {
        String ans = "";

        int tens = a/10;
        int units = a % 10;

        if (a == 100)
            return "C";
        if (a == 0)
            return "N";

        // записываем десятки
        if (tens <= 3)  // 0, 1, 2, 3
            for(int i = 1; i <= tens; ++i)
                ans += "X";
        else if (tens == 4)  // 4
            ans += "XL";
        else if (tens <= 8) // 5, 6, 7, 8
        {
            ans += "L";
            for(int i = 6; i <= tens; ++i)
                ans += "X";
        }
        else  // 9
            ans += "XC";

        // записываем единицы
        if (units < 0)
            units = -units;  // т.к. нижеследующий код рассчитан на положительные числа
        if (units <= 3)
            for(int i = 1; i <= units; ++i)
                ans += "I";
        else if (units == 4)
            ans += "IV";
        else if (units <= 8)
        {
            ans += "V";
            for(int i = 6; i <= units; ++i)
                ans += "I";
        }
        else
            ans += "IX";

        if (a < 0)
            ans = "-" + ans; // впереди нужен знак Минус

        return ans;
    }


    public static String calculate(String input) throws Exception
        {
            int answer;
            String strAnswer;

            String[] arguments = new String[2]; //массив из 2х строк
            String operation;

            if (input.indexOf("+") > -1)
            {
                arguments = input.split("\\+");
                operation = "+";
            }
            else if (input.indexOf("-") > -1)
            {
                arguments = input.split("-");
                operation = "-";
            }
            else if (input.indexOf("*") > -1)
            {
                arguments = input.split("\\*");
                operation = "*";
            }
            else if (input.indexOf("/") > -1)
            {
                arguments = input.split("/");
                operation = "/";
            }
            else
                throw new Exception("Строка не соответствует арифметической операции!");

            if (arguments.length > 2)
                throw new Exception("В строке более одного знака арифметической операции!");

            if (isArabicFigure(arguments[0]))
            {

                if (isArabicFigure(arguments[1]))
                {
                    int a = parseString(arguments[0], true);
                    if (a<1 || a>10 )
                        throw new Exception("Нет корректного первого числа от 1 до 10!");
                    int b = parseString(arguments[1], true);
                    if (b<1 || b>10 )
                        throw new Exception("Нет корректного второго числа от 1 до 10!");
                    if (operation.equals("+"))
                        answer = a + b;
                    else if (operation.equals("-"))
                        answer = a - b;
                    else if (operation.equals("*"))
                        answer = a * b;
                    else
                        answer = a / b;
                    strAnswer = String.valueOf(answer);
                }
                else
                if (isRomanFigure(arguments[1]))
                    throw new Exception("Первое число записано арабскими цифрами, а второе - римскими!");
                else
                    throw new Exception("Нет корректного второго числа от 1 до 10!");
            }
            else if (isRomanFigure(arguments[0]))
            {
                int a, b;
                try
                {
                    a = parseString(arguments[0], false);
                }
                catch (Exception ex)
                {
                    throw new Exception("Нет корректного первого числа от 1 до 10!");
                }

                if (isRomanFigure(arguments[1]))
                {
                    try
                    {
                        b = parseString(arguments[1], false);
                        if (operation.equals("+"))
                            answer = a + b;
                        else if (operation.equals("-"))
                            answer = a - b;
                        else if (operation.equals("*"))
                            answer = a * b;
                        else
                            answer = a / b;
                        strAnswer = toRomanView(answer);
                    }
                    catch (Exception ex)
                    {
                        throw new Exception("Нет корректного второго числа от 1 до 10!");
                    }
                }
                else if (isArabicFigure(arguments[1]))
                    throw new Exception("Первое число записано римскими цифрами, а второе - арабскими!");
                else
                    throw new Exception("Нет корректного второго числа от 1 до 10!");
            }
            else
                throw new Exception("Нет корректного первого числа от 1 до 10!");

            return strAnswer;
        }

        public static void main(String[] arr)
        {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            try
            {
                System.out.println(calculate(s));
            }
            catch (Exception ex)
            {
                System.out.println("Ошибка: " + ex.getMessage());
            }
        }

    }


