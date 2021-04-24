public class tile {

    protected int x;
    protected int y;

    private engimon tileEngimon;
    private char symbol;
    private String tile_type; //Hanya berupa mountains, tundra,grassland atau sea

    private Boolean tilePass;
    private Boolean isPlayer  = false;
    private Boolean isActivePokemon  = false;

    public tile(int x, int y, String type)
    {
        this.x = x;
        this.y = y;
        this.tile_type = type;
        if (tile_type.equals("mountains") )
        {
            this.symbol = ('^');
        }
        else if (tile_type.equals("sea"))
        {
            this.symbol = ('o');
        }
        else if (tile_type.equals("grassland"))
        {
            this.symbol = ('-');
        }
        else if (tile_type.equals("tundra"))
        {
            this.symbol = ('.');
        }
        this.tilePass = false;
        this.tileEngimon = null;
    }

    public tile(int x, int y, wild_engimon e, String type)
    {
        this(x,y,type);
        this.tileEngimon = e;
    }

    public Boolean isTileValid(engimon e)
    {
        if (tile_type.equals("mountains") && ("Fire".equals(e.getElmt1()) || "Fire".equals(e.getElmt2())))
        {
            return true;
        }
        else if (tile_type.equals("sea") && ("Water".equals(e.getElmt1()) || "Water".equals(e.getElmt2())))
        {
            return true;
        }
        else if ((tile_type.equals("grassland") && ("Ground".equals(e.getElmt1()) || "Ground".equals(e.getElmt2()))) ||
                (tile_type.equals("grassland") && ("Electric".equals(e.getElmt1()) || "Electric".equals(e.getElmt2()))))
        {
            return true;
        }
        else if (tile_type.equals("tundra") && ("Ice".equals(e.getElmt1()) || "Ice".equals(e.getElmt2())))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean haveWildEngimon()
    {
        return (this.tileEngimon != null);
    }

    public void passed()
    {
        this.tilePass = true;
    }
    public void resetPass()
    {
        this.tilePass = false;
    }
    public Boolean isPass()
    {
        return this.tilePass;
    }
    public void spawn()
    {
        wild_engimon we;
        try
        {
            we = new wild_engimon(this.tile_type);
            this.tileEngimon = we;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            this.tileEngimon = null;
        }



    }
    public void printTile()
    {
        System.out.print(symbol);
    }

    public void setEngimon(engimon e)
    {
        this.tileEngimon = e;
    }
    public engimon getEngimon()
    {
        return this.tileEngimon;
    }
    public void setSymbol(char symbol)
    {
        this.symbol = symbol;
    }

    public void playerIsHere()
    {
        this.isPlayer = true;
    }
    public void playerIsNotHere()
    {
        this.isPlayer = false;
    }
    public Boolean isPlayerHere()
    {
        return  this.isPlayer;
    }

    public void activeEngimonIsHere()
    {
        this.isActivePokemon = true;
    }
    public void activeEngimonIsNotHere()
    {
        this.isActivePokemon = false;
    }
    public Boolean isactiveEngimonHere()
    {
        return this.isActivePokemon;
    }

    public void updateCharacter()
    {
        if (tile_type.equals("mountains") && this.tileEngimon == null)
        {
            this.symbol = ('^');
        }
        else if (tile_type.equals("sea") && this.tileEngimon == null)
        {
            this.symbol = ('o');
        }
        else if (tile_type.equals("grassland") && this.tileEngimon == null)
        {
            this.symbol = ('-');
        }
        else if (tile_type.equals("tundra") && this.tileEngimon == null)
        {
            this.symbol = ('.');
        }
        else if (("Fire".equals(this.tileEngimon.getElmt1()) && "Electric".equals(this.tileEngimon.getElmt2())) ||
                ("Electric".equals(this.tileEngimon.getElmt1()) && "Fire".equals(this.tileEngimon.getElmt2())))
        {
            if (this.tileEngimon.getLevel() > wild_engimon.playerHighestPokemon)
            {
                this.symbol = 'L';
            }
            else
            {
                this.symbol = 'l';
            }

        }
        else if (("Water".equals(this.tileEngimon.getElmt1()) && "Ice".equals(this.tileEngimon.getElmt2())) ||
                ("Ice".equals(this.tileEngimon.getElmt1()) && "Water".equals(this.tileEngimon.getElmt2())))
        {
            if (this.tileEngimon.getLevel() > wild_engimon.playerHighestPokemon)
            {
                this.symbol = 'S';
            }
            else
            {
                this.symbol = 's';
            }

        }

        else if (("Water".equals(this.tileEngimon.getElmt1()) && "Ground".equals(this.tileEngimon.getElmt2())) ||
                ("Ground".equals(this.tileEngimon.getElmt1()) && "Water".equals(this.tileEngimon.getElmt2())))
        {
            if (this.tileEngimon.getLevel() > wild_engimon.playerHighestPokemon)
            {
                this.symbol = 'N';
            }
            else
            {
                this.symbol = 'n';
            }

        }

        else if ("Fire".equals( this.tileEngimon.getElmt1()))
        {
            if (this.tileEngimon.getLevel() > wild_engimon.playerHighestPokemon)
            {
                this.symbol = 'F';
            }
            else
            {
                this.symbol = 'f';
            }

        }
        else if ("Water".equals( this.tileEngimon.getElmt1()))
        {
            if (this.tileEngimon.getLevel() > wild_engimon.playerHighestPokemon)
            {
                this.symbol = 'W';
            }
            else
            {
                this.symbol = 'w';
            }

        }
        else if ("Electric".equals( this.tileEngimon.getElmt1()))
        {
            if (this.tileEngimon.getLevel() > wild_engimon.playerHighestPokemon)
            {
                this.symbol = 'E';
            }
            else
            {
                this.symbol = 'e';
            }
        }

        else if ("Ground".equals( this.tileEngimon.getElmt1()))
        {
            if (this.tileEngimon.getLevel() > wild_engimon.playerHighestPokemon)
            {
                this.symbol = 'G';
            }
            else
            {
                this.symbol = 'g';
            }
        }

        else if ("Ice".equals( this.tileEngimon.getElmt1()))
        {
            if (this.tileEngimon.getLevel() > wild_engimon.playerHighestPokemon)
            {
                this.symbol = 'I';
            }
            else
            {
                this.symbol = 'i';
            }
        }
        else
        {
            this.symbol = '?';
        }
    }

}
