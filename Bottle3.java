import java.util.HashSet;

public class Bottle3
{
    private String message;
    private int row;
    private int col;

    private String bottleFullName;

    private boolean stuck;

    private HashSet<String> memoryLocation;

    public Bottle3(String bottleFullName, String message, int row, int col)
    {

        this.message = message;
        this.row = row;
        this.col = col;
        this.memoryLocation = new HashSet<>();
        this.stuck = false;
        this.memoryLocation.add(row + "," + col);
        this.bottleFullName = bottleFullName;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getBottleFullName() {
        return bottleFullName;
    }

    public boolean isStuck() {
        return stuck;
    }

    public boolean isLand(char[][] map) {
        return map[row][col] == 'X';
    }

    public String getCurrentDirection(char[][] map) {
        return String.valueOf(map[row][col]);
    }

    public void moveBottle(char[][] map, int count)
    {
        if (this.stuck)
        {
            return;
        }

        String direction = getCurrentDirection(map);
        boolean willStuck;

        if (direction.equals("N"))
        {
            this.row--;
        }

        else if (direction.equals("S"))
        {
            this.row++;
        }

        else if (direction.equals("E"))
        {
            this.col++;
        }

        else if (direction.equals("W"))
        {
            this.col--;
        }

        else if (direction.equals("X"))
        {
            this.stuck = true;
            System.out.println(count + ": " + this.bottleFullName + " at (" + this.row + ", " + this.col + "): " + "LANDED!");
            System.out.println("<<" + this.message + ">>");
            return;
        }

        String newLocation = this.row + "," + this.col;
        willStuck = memoryLocation.contains(newLocation);

        if (!willStuck)
        {
            direction = getCurrentDirection(map);

            if (direction.equals("X"))
            {
                this.stuck = true;
                System.out.println(count + ": " + this.bottleFullName + " at (" + this.row + ", " + this.col + "): " + "LANDED!");
                System.out.println("<<MESSAGE RECEIVED: " + this.message + ">>");
                return;
            }

            memoryLocation.add(newLocation);
            System.out.println(count + ": " + this.bottleFullName + " at (" + this.row + ", " + this.col + "): " + "In ocean, current taking it " + direction + ".");
        }

        else
        {
            this.stuck = true;
            System.out.println(count + ": " + this.bottleFullName + " at (" + this.row + ", " + this.col + "): " + "<<NOW STUCK IN MID-OCEAN GYRE!>>");
        }

    }
}