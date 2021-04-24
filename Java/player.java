import java.util.*;  

public class player{
	protected String name;
	protected Integer max_inventory_capacity;
	protected Integer player_x;
	protected Integer player_y;
	protected Integer activeEngimon_x;
	protected Integer activeEngimon_y;

	protected engimonInventory engimon_inventory;
	protected skillInventory skill_inventory;

	public player()
	{
		this.name = "null";
		this.max_inventory_capacity = 50;
		this.player_x = 0;
		this.player_y = 0;
		this.activeEngimon_x = -1;
		this.activeEngimon_y = -1;
		this.engimon_inventory = new engimonInventory();
		this.skill_inventory = new skillInventory();
	}

	public player(String name, player_engimon starting_engimon, Integer max_inventory_capacity, Integer player_x, Integer player_y, Integer activeEngimon_x, Integer activeEngimon_y)
	{
		starting_engimon.setActive();

		this.name = name;
		this.max_inventory_capacity = max_inventory_capacity;
		this.player_x = player_x;
		this.player_y = player_y;
		this.activeEngimon_x = activeEngimon_x;
		this.activeEngimon_y = activeEngimon_y;
		this.engimon_inventory = new engimonInventory();
		this.skill_inventory = new skillInventory();
		this.addInventoryContent(starting_engimon);
	}

	public player_engimon getActiveEngimon()
	{
		int i;
		for (i = 0; i < this.engimon_inventory.getContents().size(); i++)
		{
			if (this.engimon_inventory.getContents().get(i).isActive())
			{
				break;
			}
		}

		if (i != this.engimon_inventory.getContents().size())
		{
			return(this.engimon_inventory.getContents().get(i));
		}
		else
		{
			return null;
		}
	}

	public void deleteActiveEngimon()
	{
		if (this.getActiveEngimon() != null)
		{
			deleteInventoryContent(this.getActiveEngimon());
		}
		else
		{
			System.out.println("Tidak ada engimon yang sedang aktif");
		}
	}

	public void moveDown(Integer mapLength, Integer mapWidth)
	{
		if (player_y < mapWidth - 1){
			Integer oldPosX = player_x;	// Buat posisi engimon aktif ikuti posisi player sebelum pindah
			Integer oldPosY = player_y;
			player_y += 1;
			activeEngimon_x = oldPosX;
			activeEngimon_y = oldPosY;
		}
	}

	public void moveUp(Integer mapLength, Integer mapWidth)
	{
		if (player_y > 0){
			Integer oldPosX = player_x;	// Buat posisi engimon aktif ikuti posisi player sebelum pindah
			Integer oldPosY = player_y;
			player_y -= 1;
			activeEngimon_x = oldPosX;
			activeEngimon_y = oldPosY;
		}
	}

	public void moveLeft(Integer mapLength, Integer mapWidth)
	{
		if (player_x > 0){
			Integer oldPosX = player_x;	// Buat posisi engimon aktif ikuti posisi player sebelum pindah
			Integer oldPosY = player_y;
			player_x -= 1;
			activeEngimon_x = oldPosX;
			activeEngimon_y = oldPosY;
		}
	}

	public void moveRight(Integer mapLength, Integer mapWidth)
	{
		if (player_x < mapLength - 1){
			Integer oldPosX = player_x;	// Buat posisi engimon aktif ikuti posisi player sebelum pindah
			Integer oldPosY = player_y;
			player_x += 1;
			activeEngimon_x = oldPosX;
			activeEngimon_y = oldPosY;
		}
	}

	public void setEngimonName(int index, String name)
	{
		this.engimon_inventory.getContents().get(index).setName(name);
	}

	public int getHighestLevelEngimon()
	{
		int max = -1;
		for (int i = 0; i < this.engimon_inventory.getContents().size(); i++)
		{
			if (this.engimon_inventory.getContents().get(i).getLevel() > max)
			{
				max = this.engimon_inventory.getContents().get(i).getLevel();
			}
		}
		return max;
	}

	public void addInventoryContent(player_engimon engimon)
	{
		if(!this.isInventoryFull())
		{
			this.engimon_inventory.addItem(engimon);
		}
		else
		{
			System.out.println("Inventory sudah penuh");
		}
	}

	public void deleteInventoryContent(player_engimon engimon)
	{
		this.engimon_inventory.deleteItem(engimon);
	}

	public void addInventoryContent(String skillName)
	{
		database_skill database = new database_skill();
		skill skill_item = database.find(skillName);
		if (skill_item.getSkillName() != "null")
		{	
			if (!this.isInventoryFull())
			{
				this.skill_inventory.addItem(skill_item);
			}
			else
			{
				System.out.println("Inventory sudah penuh");
			}
		}
	}

	public void deleteInventoryContent(String skillName, int amount)
	{
		database_skill database = new database_skill();
		skill skill_item = database.find(skillName);
		for (int i = 0; i < amount; i++)
		{
			this.skill_inventory.deleteItem(skill_item);
		}
	}

	public void showEngimonList()
	{
		if (this.engimon_inventory.countItem() > 0)
		{
			System.out.println("List engimon kamu: ");

			Integer count = 1;
			int i;
			for (i = 0; i != this.engimon_inventory.getContents().size(); ++i)
			{
				System.out.print(count + ". " + this.engimon_inventory.getContents().get(i).getName() + " (" + this.engimon_inventory.getContents().get(i).getSpecies() + "/" + this.engimon_inventory.getContents().get(i).getElmt1());
				if (this.engimon_inventory.getContents().get(i).getElmt2() != null)
				{
					System.out.print("|" + this.engimon_inventory.getContents().get(i).getElmt2());
				}
				System.out.print("/lv " + this.engimon_inventory.getContents().get(i).getLevel().toString() + ")");
				if (this.engimon_inventory.getContents().get(i).isActive())
				{
					System.out.print(" [active]");
				}
				System.out.println();

				count++;
			}
		}
		else
		{
			System.out.println("Anda tidak memiliki engimon");
		}
	}

	public void showSkillItemList()
	{
		if (this.skill_inventory.countItem() > 0)
		{
			System.out.println("List skill item kamu: ");

			Integer count = 1;
			int i;
			for (i = 0; i != this.skill_inventory.getContents().size(); ++i)
			{
				System.out.println(count + ". " + this.skill_inventory.getContents().get(i).getSkillName() + " (base power: " + Integer.toString(this.skill_inventory.getContents().get(i).getBasePower()) + ") [" + Integer.toString(this.skill_inventory.getContents().get(i).getAmountInInventory()) + "]");
				count++;
			}
		}
		else
		{
			System.out.println("Anda tidak memiliki skill item");
		}
	}

	public void deleteEngimonSelect(int index)
	{
		this.deleteInventoryContent(this.engimon_inventory.getContents().get(index));
	}

	public void deleteEngimonSelect()
	{
		Integer i;
		Scanner sc= new Scanner(System.in);
		if (this.engimon_inventory.countItem() > 0) {
			this.showEngimonList();
			System.out.println("Ketik nomor engimon yang ingin dibuang: ");
			i = sc.nextInt();
			if (i >= 1 && i <= this.engimon_inventory.getContents().size())
			{
				deleteInventoryContent(this.engimon_inventory.getContents().get(i-1));
			}
		}
		else
		{
			System.out.println("Anda tidak memiliki engimon");
		}
		sc.close();
	}

	public void showEngimonDetails()
	{
		Integer i;
		Scanner sc = new Scanner(System.in);
		if (this.engimon_inventory.countItem() > 0) {
			this.showEngimonList();
			System.out.println("Ketik nomor yang ada untuk melihat detail engimon: ");
			i = sc.nextInt();
			if (i >= 1 && i <= this.engimon_inventory.getContents().size())
			{
				this.engimon_inventory.getContents().get(i-1).showAttributes();
			}
		}
		else
		{
			System.out.println("Anda tidak memiliki engimon");
		}
		sc.close();
	}

	public void switchOutEngimon(int index)
	{
		if (this.getActiveEngimon() != null)
		{
			this.getActiveEngimon().setInactive();
		}

		this.engimon_inventory.getContents().get(index).setActive();
	}

	public void switchOutEngimon()
	{
		Integer i;
		Scanner sc = new Scanner(System.in);
		if (this.engimon_inventory.countItem() > 0) {
			this.showEngimonList();
			System.out.println("Ketik nomor engimon yang ingin dibuat aktif: ");
			i = sc.nextInt();
			if (i >= 1 && i <= this.engimon_inventory.getContents().size())
			{
				if (getActiveEngimon() != null){
					if (this.engimon_inventory.getContents().get(i-1).isActive()){
						System.out.println("Engimon tersebut sedang aktif");
					}
					else{
						System.out.println("Replace active Engimon " + getActiveEngimon().getName() + " dengan Engimon " + this.engimon_inventory.getContents().get(i-1).getName());
						getActiveEngimon().setInactive();
						this.switchOutEngimon(i-1);
					}
				}
				else{
					this.switchOutEngimon(i-1);
					System.out.println("Set " + this.engimon_inventory.getContents().get(i-1).getName() + " sebagai active Engimon");
					if (player_x != 0){
						activeEngimon_x = player_x - 1;
						activeEngimon_y = player_y;
					}
					else{
						activeEngimon_x = player_x + 1;
						activeEngimon_y = player_y;
					}
				}
			}
		}
		else
		{
			System.out.println("Anda tidak memiliki engimon");
		}
		sc.close();
	}

	public void Interact()
	{
		if (this.getActiveEngimon() != null)
		{
			this.getActiveEngimon().cry();
		}
		else
		{
			System.out.println("Tidak ada engimon yang sedang aktif");
		}
	}

	public void useSkillItem(int skillIdx, int engimonIdx)
	{
		try
		{
			this.engimon_inventory.getContents().get(engimonIdx).learnMove(this.skill_inventory.getContents().get(skillIdx).getSkillName());
			this.deleteInventoryContent(this.skill_inventory.getContents().get(skillIdx).getSkillName(), 1);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public void useSkillItem() throws Exception
	{
		Integer i;
		Integer j;
		Scanner sc = new Scanner(System.in);
		if (this.skill_inventory.countItem() > 0 && this.engimon_inventory.countItem() > 0 ) {
			this.showSkillItemList();
			System.out.println("Ketik nomor skill yang ingin dipakai: ");
			i = sc.nextInt();
			if (i >= 1 && i <= this.skill_inventory.getContents().size())
			{
				this.showEngimonList();
				System.out.println("Ketik nomor engimon yang ingin learn skill " + this.skill_inventory.getContents().get(i-1).getSkillName() + " : ");
				j = sc.nextInt();
				if (j >= 1 && j <= this.engimon_inventory.getContents().size())
				{
					try
					{
						this.engimon_inventory.getContents().get(j-1).learnMove(this.skill_inventory.getContents().get(i-1).getSkillName());
						deleteInventoryContent(this.skill_inventory.getContents().get(i-1).getSkillName(), 1);
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
					
					/*
					catch (Integer e)
					{
						if (e == -1) System.out.println("Error, skill tidak ditemukan");
						if (e == -2) System.out.println("Error, engimon tidak sesuai");
						if (e == -3) System.out.println("Error, skill tidak compatible dengan elemen engimon");
						if (e == -4) System.out.println("Error, skill sudah ada");
					}
					*/
				}
			}
		}
		else
		{
			System.out.println("Anda harus memiliki minimal 1 engimon dan item skill");
		}
		sc.close();
	}

	public void breeding(int engimonIdx1, int engimonIdx2, String name) throws Exception
	{
		player_engimon newEngimon = breeder.breed(name, this.engimon_inventory.getContents().get(engimonIdx1), this.engimon_inventory.getContents().get(engimonIdx2));
		this.addInventoryContent(newEngimon);
	}

	public void breeding() throws Exception 
	{
		Integer i;
		Integer j;
		String name;
		Scanner sc = new Scanner(System.in);
		if (!this.isInventoryFull())
		{
			if (this.engimon_inventory.countItem() >= 2)
			{
				this.showEngimonList();
				System.out.println("Ketik nomor engimon pertama yang ingin breeding\n");
				i = sc.nextInt();
				if (i >= 1 && i <= this.engimon_inventory.getContents().size())
				{
					this.showEngimonList();
					System.out.println("Ketik nomor engimon kedua yang ingin breeding\n");
					j = sc.nextInt();
					if (j >= 1 && j <= this.engimon_inventory.getContents().size())
					{
						if (i == j)
						{
							sc.close();
							throw new Exception( "Tidak bisa breeding engimon yang sama");
						}
						else
						{
							System.out.println("Ketik nama engimon hasil breeding\n");
							name = sc.nextLine();
							player_engimon newEngimon = breeder.breed(name, this.engimon_inventory.getContents().get(i-1), this.engimon_inventory.getContents().get(j-1));
							this.addInventoryContent(newEngimon);
						}
					}
				}
			}
			else
			{
				sc.close();
				throw new Exception("Kamu tidak memiliki engimon yang cukup untuk breeding");
			}
		}
		else
		{
			sc.close();
			throw new Exception("Inventory kamu sudah penuh");
		}
		sc.close();
	}

		

		/*
		try {
			Integer i;
			Integer j;
			String name;
			if (!this.isInventoryFull())
			{
				if (this.engimon_inventory.countItem() >= 2)
				{
					this.showEngimonList();
					System.out.println("Ketik nomor engimon pertama yang ingin breeding\n");
					//cin >> i;
					if (i >= 1 && i <= this.engimon_inventory.getContents().size())
					{
						this.showEngimonList();
						System.out.println("Ketik nomor engimon kedua yang ingin breeding\n");
						//cin >> j;
						if (j >= 1 && j <= this.engimon_inventory.getContents().size())
						{
							if (i == j)
							{
								throw "Tidak bisa breeding engimon yang sama";
							}
							else
							{
								System.out.println("Ketik nama engimon hasil breeding\n");
								//cin >> name;
								engimon newEngimon = breed(this.engimon_inventory.getContents()[i-1], this.engimon_inventory.getContents()[j-1], name);
								this.addInventoryContent(newEngimon);
							}
						}
					}
				}
				else
				{
					throw "Kamu tidak memiliki engimon yang cukup untuk breeding";
				}
			}
			else
			{
				throw "Inventory kamu sudah penuh";
			}
		}
		catch (String e)
		{
			System.out.println(e);
		}
	}
	*/

	public Boolean isInventoryFull()
	{
		return (this.engimon_inventory.countItem() + this.skill_inventory.countItem() >= this.max_inventory_capacity);
	}

	// Posisi activeEngimon_x
	public Integer getActivePetPosX() {return activeEngimon_x;}
	// Posisi activeEngimon_y
	public Integer getActivePetPosY() {return activeEngimon_y;}
	// Set posisi activeEngimon_x
	public void setActivePetPosX(Integer x) {this.activeEngimon_x = x;}
	// Set posisi activeEngimon_x
	public void setActivePetPosY(Integer y) {this.activeEngimon_y = y;}

	// Posisi player x
	public Integer getPlayerPosX() {return player_x;}
	// Posisi player y
	public Integer getPlayerPosY() {return player_y;}
	// Set posisi player x
	public void setPlayerPosX(Integer x) {this.player_x = x;}
	// Set posisi player y
	public void setPlayerPosY(Integer y) {this.player_y = y;}

	//Nama player
	public String getName() {return name;}
}