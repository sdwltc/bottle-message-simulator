import java.util.*;
public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        // 读入地图大小
        //Read the map size
        int totalRow = in.nextInt();
        int totalCol = in.nextInt();
        in.nextLine();


        // 读入坐标，并将陆地位置填充X
        //Read the coordinates and fill the land position with X
        char[][] map = new char[totalRow][totalCol];
        String[] rawLocation = in.nextLine().split(" ");
        for (String temp : rawLocation)
        {
            String[] landLocation = temp.split(",");
            int row = Integer.parseInt(landLocation[0]);
            int col = Integer.parseInt(landLocation[1]);
            map[row][col] = 'X';
        }

        //读入方向，空格分开，并将方向填充进地图的空白处
        //Read the direction, separate the spaces, and fill the direction into the blank space in the map
        String rawDirection = in.nextLine();
        char[] goodDirection = rawDirection.replaceAll("\\s", "").toCharArray();
        int index = 0;
        for (int i = 0; i < totalRow; i++)
        {
            for (int j = 0; j < totalCol; j++)
            {
                //判定是不是null
                //decided blank then put direction
                if (map[i][j] == '\0')
                {
                    if (index < goodDirection.length)
                    {
                        map[i][j] = goodDirection[index];
                        index++;
                    }
                    else
                    {
                        break;
                    }
                }
            }
            if (index >= goodDirection.length)
            {
                break;
            }
        }


        int count = 0;


        //读取瓶子个数
        //Read the number of bottles
        int bottleNum = in.nextInt();
        in.nextLine();

        //读取瓶子位置和名字
        //Read the location and name of the bottle
        Bottle3[] bottle3 = new Bottle3[bottleNum];
        for(int i = 0; i < bottleNum; i++)
        {
            String[] bottleInfo = in.nextLine().split(" ");
            int locationRow = Integer.parseInt(bottleInfo[0]);
            int locationCol = Integer.parseInt(bottleInfo[1]);

            // 处理名字
            //deal with name
            String fullName = String.join(" ", Arrays.copyOfRange(bottleInfo, 2, bottleInfo.length)).trim();

            //读取一段话
            //Read a paragraph
            String message = in.nextLine().trim();
            bottle3[i] = new Bottle3(fullName, message, locationRow, locationCol);


        }


        // 打印最开始的句子
        // print the initial start sentence
        for(int i = 0; i < bottleNum; i++)
        {
            String fullName = bottle3[i].getBottleFullName();
            int locationRow = bottle3[i].getRow();
            int locationCol = bottle3[i].getCol();
            System.out.println(fullName + ": Starting at (" + locationRow + ", " + locationCol + ")");
        }

        // 打印从0开始的句子
        // print the sentence from 0
        for(int i = 0; i < bottleNum; i++)
        {
            String fullName = bottle3[i].getBottleFullName();
            int locationRow = bottle3[i].getRow();
            int locationCol = bottle3[i].getCol();
            System.out.println(count + ": " + fullName + " at (" + locationRow + ", " + locationCol + "): " + "In ocean, current taking it " + map[locationRow][locationCol] + ".");
        }





        //开始移动
        //start moving
        while (true)
        {
            count++;
            for (int i = 0; i < bottleNum; i++)
            {
                bottle3[i].moveBottle(map, count);
            }


            boolean isAllStuck = true;
            for (int i = 0; i < bottleNum; i++)
            {
                if (!bottle3[i].isStuck())
                {
                    isAllStuck = false;
                }
            }

            if (isAllStuck)
            {
                break;
            }

        }

    }
}