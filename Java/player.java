public class player{
	protected String name;
	protected Integer max_inventory_capacity;
	protected Integer player_x;
	protected Integer player_y;
	protected Integer activeEngimon_x;
	protected Integer activeEngimon_y;

	protected inventory<engimon> engimon_inventory;
	protected inventory<skill> skill_inventory;
	protected engimon nullEngimon;

	public player()
	{
		this.name = "null";
		this.max_inventory_capacity = 50;
		this.player_x = 0;
		this.player_y = 0;
		this.activeEngimon_x = -1;
		this.activeEngimon_y = -1;
	}

	public player(String name, engimon starting_engimon, Integer max_inventory_capacity, Integer player_x, Integer player_y, Integer activeEngimon_x, Integer activeEngimon_y)
	{
		starting_engimon.setActive();

		this.name = name;
		this.max_inventory_capacity = max_inventory_capacity;
		this.player_x = player_x;
		this.player_y = player_y;
		this.activeEngimon_x = activeEngimon_x;
		this.activeEngimon_y = activeEngimon_y;
		this.addInventoryContent(starting_engimon);
	}

	public player(player play)
	{
		this.name = play.name;
		this.max_inventory_capacity = play.max_inventory_capacity;
		this.player_x = play.player_x;
		this.player_y = play.player_y;
		this.activeEngimon_x = play.activeEngimon_x;
		this.activeEngimon_y = play.activeEngimon_y;
		copy(play.engimon_inventory.contents.begin(), play.engimon_inventory.contents.end(), back_inserter(this.engimon_inventory.contents)); 
		copy(play.skill_inventory.contents.begin(), play.skill_inventory.contents.end(), back_inserter(this.skill_inventory.contents)); 
	}

	public engimon getActiveEngimon()
	{
		auto i = this.engimon_inventory.contents.begin();
		for (i = this.engimon_inventory.contents.begin(); i != this.engimon_inventory.contents.end(); ++i)
		{
			if ((i).isActive())
			{
				break;
			}
		}

		if (i != this.engimon_inventory.contents.end())
		{
			return(i);
		}
		else
		{
			return nullEngimon;
		}
	}

	public void deleteActiveEngimon()
	{
		if (this.getActiveEngimon().getName() != "null")
		{
			deleteInventoryContent(this.getActiveEngimon());
			if (this.engimon_inventory.countItem() > 0)
			{
				this.engimon_inventory.contents[0].setActive();
			}
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

	public void addInventoryContent(engimon engimon)
	{
		if (engimon.getName() != "null")
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
	}

	public void deleteInventoryContent(engimon engimon)
	{
		this.engimon_inventory.deleteItem(engimon);
	}

	public void addInventoryContent(String skillName) throws Exception
	{
		if (skillName != "null")
		{	
			if (!this.isInventoryFull())
			{
				auto itr = skill_database.begin();
				for (itr = skill_database.begin(); itr != skill_database.end(); ++itr)
					if ((itr).getSkillName() == skillName)	
					{
						break;
					}
				
				if (itr == skill_database.end())
				{
					throw new Exception("Error, skill tidak ditemukan");
				}
				
				this.skill_inventory.addItem((itr));
			}
			else
			{
				System.out.println("Inventory sudah penuh");
			}
		}
	}

	public void deleteInventoryContent(String skillName) throws Exception
	{
		auto itr = skill_database.begin();
		for (itr = skill_database.begin(); itr != skill_database.end(); ++itr)
			if ((itr).getSkillName() == skillName)	
			{
				break;
			}
		
		if (itr == skill_database.end())
		{
			throw new Exception("Error, skill tidak ditemukan");
		}
		this.skill_inventory.deleteItem((itr));
	}

	public void showEngimonList()
	{
		if (this.engimon_inventory.countItem() > 0)
		{
			System.out.println("List engimon kamu: ");

			Integer count = 1;
			auto i = this.engimon_inventory.contents.begin();
			for (i = this.engimon_inventory.contents.begin(); i != this.engimon_inventory.contents.end(); ++i)
			{
				if ((i).isActive())
				{
					System.out.println(count + ". " + (i).getName() + " (" + (i).getSpecies() + " lv " + (i).getLevel() + ")" + " [active]");
				}
				else
				{
					System.out.println(count + ". " + (i).getName() + " (" + (i).getSpecies() + " lv " + (i).getLevel() + ")" );
				}
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
			auto i = this.skill_inventory.contents.begin();
			for (i = this.skill_inventory.contents.begin(); i != this.skill_inventory.contents.end(); ++i)
			{
				System.out.println(count + ". " + (i).getSkillName() + " (" + (i).getAmountInInventory() + ")");
				count++;
			}
		}
		else
		{
			System.out.println("Anda tidak memiliki skill item");
		}
	}

	public void deleteEngimonSelect()
	{
		Integer i;
		if (this.engimon_inventory.countItem() > 0) {
			this.showEngimonList();
			System.out.println("Ketik nomor engimon yang ingin dibuang: ");
			//cin >> i;
			if (i >= 1 && i <= this.engimon_inventory.contents.size())
			{
				deleteInventoryContent(this.engimon_inventory.contents[i-1]);
			}
		}
		else
		{
			System.out.println("Anda tidak memiliki engimon");
		}
	}

	public void showEngimonDetails()
	{
		Integer i;
		if (this.engimon_inventory.countItem() > 0) {
			this.showEngimonList();
			System.out.println("Ketik nomor yang ada untuk melihat detail engimon: ");
			//cin >> i;
			if (i >= 1 && i <= this.engimon_inventory.contents.size())
			{
				this.engimon_inventory.contents[i-1].showAttributes();
			}
		}
		else
		{
			System.out.println("Anda tidak memiliki engimon");
		}
	}

	public void switchOutEngimon()
	{
		Integer i;
		if (this.engimon_inventory.countItem() > 0) {
			this.showEngimonList();
			System.out.println("Ketik nomor engimon yang ingin dibuat aktif: ");
			//cin >> i;
			if (i >= 1 && i <= this.engimon_inventory.contents.size())
			{
				if (getActiveEngimon().getName() != "null"){
					if (this.engimon_inventory.contents[i-1].isActive()){
						System.out.println("Engimon tersebut sedang aktif");
					}
					else{
						System.out.println("Replace active Engimon " + getActiveEngimon().getName() + " dengan Engimon " + this.engimon_inventory.contents[i-1].getName());
						getActiveEngimon().setInactive();
						this.engimon_inventory.contents[i-1].setActive();
					}
				}
				else{
					this.engimon_inventory.contents[i-1].setActive();
					System.out.println("Set " + this.engimon_inventory.contents[i-1].getName() + " sebagai active Engimon");
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
	}

	public void Integereract()
	{
		if (this.getActiveEngimon().getName() != "null")
		{
			this.getActiveEngimon().cry();
		}
		else
		{
			System.out.println("Tidak ada engimon yang sedang aktif");
		}
	}

	public void useSkillItem() throws Exception
	{
		Integer i;
		Integer j;
		if (this.skill_inventory.countItem() > 0 && this.engimon_inventory.countItem() > 0 ) {
			this.showSkillItemList();
			System.out.println("Ketik nomor skill yang ingin dipakai: ");
			//cin >> i;
			if (i >= 1 && i <= this.skill_inventory.contents.size())
			{
				this.showEngimonList();
				System.out.println("Ketik nomor engimon yang ingin learn skill " + this.skill_inventory.contents[i-1].getSkillName() + " : ");
				//cin >> j;
				if (j >= 1 && j <= this.engimon_inventory.contents.size())
				{
					engimon dummy = this.engimon_inventory.contents[j-1];	// hapus ini
					//engimon dummy = new engimon(this.engimon_inventory.contents[j-1]);	// perbaiki ini
					dummy.learnMove(this.skill_inventory.contents[i-1].getSkillName());
					this.engimon_inventory.contents[j-1].learnMove(this.skill_inventory.contents[i-1].getSkillName());
					deleteInventoryContent(this.skill_inventory.contents[i-1].getSkillName());
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
	}

	public void breeding() throws Exception 
	{
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
				if (i >= 1 && i <= this.engimon_inventory.contents.size())
				{
					this.showEngimonList();
					System.out.println("Ketik nomor engimon kedua yang ingin breeding\n");
					//cin >> j;
					if (j >= 1 && j <= this.engimon_inventory.contents.size())
					{
						if (i == j)
						{
							throw new Exception( "Tidak bisa breeding engimon yang sama");
						}
						else
						{
							System.out.println("Ketik nama engimon hasil breeding\n");
							//cin >> name;
							engimon newEngimon = breed(this.engimon_inventory.contents[i-1], this.engimon_inventory.contents[j-1], name);
							this.addInventoryContent(newEngimon);
						}
					}
				}
			}
			else
			{
				throw new Exception("Kamu tidak memiliki engimon yang cukup untuk breeding");
			}
		}
		else
		{
			throw new Exception("Inventory kamu sudah penuh");
		}
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
					if (i >= 1 && i <= this.engimon_inventory.contents.size())
					{
						this.showEngimonList();
						System.out.println("Ketik nomor engimon kedua yang ingin breeding\n");
						//cin >> j;
						if (j >= 1 && j <= this.engimon_inventory.contents.size())
						{
							if (i == j)
							{
								throw "Tidak bisa breeding engimon yang sama";
							}
							else
							{
								System.out.println("Ketik nama engimon hasil breeding\n");
								//cin >> name;
								engimon newEngimon = breed(this.engimon_inventory.contents[i-1], this.engimon_inventory.contents[j-1], name);
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