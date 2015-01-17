## ClugMSS (MineServer Alpha)
This is a project I've been working on for the last month , which is supposed to be an alternative to the Bukkit platform , 
which was taken down.


-
---------------------------------------------------------------------------------------------------------------------------





#Download Instructions

Downloads can be found at: https://www.dropbox.com/home/Apps/ClugMSS%20installer/ClugMSS which are made to any build since 17/01/15 by a homemade auto-compiler && auto commit maker.
After you've downloaded one of the builds , download MinecraftServer from the minecraft website , open the "toJar" folder in the zip , and copy it's contents to the MinecraftServer jar , and then you are done

#Installation
The server is like a normal server but you need to make 3 folders in the containing folder of the jar:
  
  1.plugins : for plugins
  
  2.data : for NBT data
  
  3.perms : for commands permissions

---------------------------------------------------------------------------------------------------------------------------
#The Permissions File
[NAME]

[UNLOCALISED_NAME]

players:

[PLAYER1]

[PLAYER2]

commands:

[COMMAND1]

[COMMAND2]

[COLOR]

the file name should be "NAME.mpc".
NAME stands for fileName
UNLOCALISED_NAME stands for code name
COMMAND can be 
  
  1.A command name , For Example: "tp" (Enables TP)
  
  2.A disabled command , For Example : "-tp" (Disables TP)
  
  3.A statement:
  
  "perms.all" - all commands / permissions
  
  "perms.mine_blocks" - permission to mine blocks
  
  "-perms.mine_blocks" - disabling the option to mine blocks when all permissions are allowed

COLOR stands for color , which can be used for chat.

