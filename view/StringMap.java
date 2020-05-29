package Maze.view;


import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Maze.view.Window;
import Maze.cont.*;
import Maze.mod.*;
import Maze.mod.Player;

public class StringMap extends JFrame{
	/*
	 * World 1
	 * Declare and initialize all private instance variables
	 */
	private static final long serialVersionUID = 1L;
	private JPanel _map = new JPanel(new GridLayout(21,21));
	
	private JLabel[][] Maze = new JLabel[21][21];
	private int level = 0;
	
	private int cnt = 39;//# of steps player can take

	/*
	 * Options displayed when certain thing happens in the game
	 */
	private static final String[] WORLDS = {"World 2", "World 3", "World 4", "World 5", "STAY IN THIS WORLD"};
	private static final String[] RESTART = {"Restart GAME!", "Exit"};

	boolean onEx = false;
	
	//Getter
	public JPanel getMap(){ return _map; }
	public boolean getEx() { return onEx; }
	public int getCnt() { return cnt; }

	//Setter
	public void setExTrue() {
		onEx = true;
	}
	//everything 28x28
	//wall and player 30x30
	//All GUI Images to be used
	private Icon player = new ImageIcon(getClass().getResource("mar.png"));
	private Icon floor = new ImageIcon(getClass().getResource("grass.png"));
	private Icon wall = new ImageIcon(getClass().getResource("wall.png"));
	private Icon minotaur = new ImageIcon(getClass().getResource("larryicon.png"));
	private Icon exit = new ImageIcon(getClass().getResource("pipe1.png"));
	private Icon rat1 = new ImageIcon(getClass().getResource("lemmy.png"));
	private Icon rat2 = new ImageIcon(getClass().getResource("kooptroop.png"));//koop
	private Icon rat3 = new ImageIcon(getClass().getResource("kooptroop.png"));//koop
	private Icon sword = new ImageIcon(getClass().getResource("link.png"));
	private Icon tele = new ImageIcon(getClass().getResource("cannonx.png"));
	private Icon quest = new ImageIcon(getClass().getResource("question.png"));
	private Icon gameOver = new ImageIcon(getClass().getResource("bows.png"));
	private Icon lvlSelect = new ImageIcon(getClass().getResource("WorldAll.png"));
	private Icon cannon5 = new ImageIcon(getClass().getResource("cannon5.jpg"));
	private Icon cannon2 = new ImageIcon(getClass().getResource("cannon2.png"));
	private Icon cannon3 = new ImageIcon(getClass().getResource("cannon3.png"));
	private Icon cannon4 = new ImageIcon(getClass().getResource("cannon4.png"));
	private ImageIcon world2 =  new ImageIcon((getClass().getResource("world2.png")));
	private ImageIcon world3 =  new ImageIcon((getClass().getResource("world3.png")));
	private ImageIcon world4 =  new ImageIcon((getClass().getResource("world4.png")));
	private ImageIcon world5 =  new ImageIcon((getClass().getResource("world5.png")));
	private ImageIcon think =  new ImageIcon((getClass().getResource("think.png")));
	private Icon go = new ImageIcon(getClass().getResource("lets go.png"));
	private Icon pipe = new ImageIcon(getClass().getResource("pip.png"));
	private Icon ex = new ImageIcon(getClass().getResource("ex.png"));
	private Icon home = new ImageIcon(getClass().getResource("mansion.png"));
	private Icon lnksword = new ImageIcon(getClass().getResource("sword.png"));
	boolean link = false;	
	private ImageIcon lnk =  new ImageIcon((getClass().getResource("link icon.png")));

	//Used for opening scene of teleporting to World 4
	public void home() {
		Window.msg2("This world used to be colorful like a rainbow, but now it has become the hub of Boos!"
				+ "\n Maybe you can help! "
				+ "BE CAREFUL: Boos can dissapear and reappear...you will need some light!", home);
	}
	
	//Updates the top part of the game where the number of steps a player can take is updated on the top left corner
	 public void updateCnt() {
			String s = "Number of Turns Left: "+ getCnt();
			Border border = BorderFactory.createTitledBorder(s);
			_map.setBorder(border);
	 }
	 
	 //Updates all inputs and handles all movements in the game
	public JPanel updateMap(Player _p, Maze z, String userIn, Minotaur m, Rat r1, Rat r2, Rat r3, 
			Sword s, Teleport teleport, Question _q, Exclamation _ex) {
		_map = new JPanel(new GridLayout(21,21));	
		
		if (userIn.equals("NORTH")) {
			updateCnt();
			if (z.getMaze()[_p.getPosition().getRow()-1][_p.getPosition().getCol()] == false) {
                _p.move(Direction.UP, z);
                move(_p, z, m, r1, r2, r3, s, _q, _ex);
                gamecheck1(_p, z, m, r1, r2, r3, s, teleport, _q, _ex);
                cnt--;
                if(cnt<-1) {
                	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
        			askRestart();
        			}            } 
        }
		else if (userIn.equals("SOUTH")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()+1][_p.getPosition().getCol()] == false) {
                _p.move(Direction.DOWN, z);
                move(_p, z, m, r1, r2, r3, s, _q, _ex);
                gamecheck1(_p, z, m, r1, r2, r3, s, teleport, _q, _ex);
                cnt--;
                if(cnt<-1) {
                	Window.msg2("Mario is too tired and cannot move anymore!\nBowser has won, keeping Peach forever...", gameOver);
                	askRestart();
        			}            } 
        }
		else if (userIn.equals("EAST")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()][_p.getPosition().getCol()+1] == false) {
               _p.move(Direction.RIGHT, z);
               move(_p, z, m, r1, r2, r3, s, _q, _ex);
               gamecheck1(_p, z, m, r1, r2, r3, s, teleport, _q, _ex);
               cnt--;
               if(cnt<-1) {
               	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
               	askRestart();
       			}            } 
        }
		else if (userIn.equals("WEST")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()][_p.getPosition().getCol()-1] == false) {
                _p.move(Direction.LEFT, z);
                move(_p, z, m, r1, r2, r3, s, _q, _ex);
                gamecheck1(_p, z, m, r1, r2, r3, s, teleport, _q, _ex);
                cnt--;
                if(cnt<-1) {
                	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
                	askRestart();
        			}              /*  if (z.getMaze()[p.getPosition().getRow()][p.getPosition().getCol()-1] == true) {
					Window.msg("MARIO CANNOT WALL JUMP FOR A SHORT CUT, HE WILL HAVE TO TAKE THE LONG WAY!");
                }*/
            } 
        }
		
		//GUI where we add each imageicon corresponding to the character/object
		for(int r = 0; r < z.getMaze().length; r++) {
			for(int c = 0; c < z.getMaze()[r].length; c++) {
				if(z.getMaze()[r][c]) {
					Maze[r][c] = new JLabel(wall);
					_map.add(Maze[r][c]);	
				}
				else if(r == _p.getPosition().getRow() &&
						c == _p.getPosition().getCol()) {
					if(link == false) {
					Maze[r][c] = new JLabel(player);
					_map.add(Maze[r][c]);
					}
					else {
						Maze[r][c] = new JLabel(lnk);
						_map.add(Maze[r][c]);
					}
				}
				else if(r == m.getPosition().getRow() &&
						c == m.getPosition().getCol()) {
					Maze[r][c] = new JLabel(minotaur);
					_map.add(Maze[r][c]);	
				}
				else if(r == r1.getPosition1().getRow() &&
						c == r1.getPosition1().getCol()) {
					Maze[r][c] = new JLabel(rat1);
					_map.add(Maze[r][c]);
				}
				
				else if(r == r2.getPosition2().getRow() &&
						c == r2.getPosition2().getCol()) {
					Maze[r][c] = new JLabel(rat2);
					_map.add(Maze[r][c]);
				}
				else if(r == r3.getPosition3().getRow() &&
						c == r3.getPosition3().getCol()) {
					Maze[r][c] = new JLabel(rat3);
					_map.add(Maze[r][c]);
				}
				else if(r == s.getPosition().getRow() &&
						c == s.getPosition().getCol()) {
					Maze[r][c] = new JLabel(sword);
					_map.add(Maze[r][c]);						
				}
				else if(r == _q.getQuestPos1().getRow() &&
						c == _q.getQuestPos1().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				else if(r == _q.getQuestPos2().getRow() &&
						c == _q.getQuestPos2().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				else if(r == _q.getQuestPos3().getRow() &&
						c == _q.getQuestPos3().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				else if(r == _q.getQuestPos4().getRow() &&
						c == _q.getQuestPos4().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				else if(r == _q.getQuestPos5().getRow() &&
						c == _q.getQuestPos5().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				
				else if(r == teleport.getPosition().getRow() &&
						c == teleport.getPosition().getCol()) {
					Maze[r][c] = new JLabel(tele);
					_map.add(Maze[r][c]);						
				}
				else if(r == _ex.getPosition().getRow() &&
						c == _ex.getPosition().getCol()) {
					Maze[r][c] = new JLabel(ex);
					_map.add(Maze[r][c]);						
				}
				else if(r == z.getEnd().getRow() &&
						c == z.getEnd().getCol()) {
					if(getEx()) {
					Maze[r][c] = new JLabel(exit);
					_map.add(Maze[r][c]);	
					} 
					else {
						Maze[r][c] = new JLabel(floor);
						_map.add(Maze[r][c]);	
					}
				}
				else {
					Maze[r][c] = new JLabel(floor);
					_map.add(Maze[r][c]);	
				}
			}
		}
		return _map;
	}
	
	//Checks if enemies are alive to allow them to move
	public void move(Player _p, Maze _z, Minotaur _m, Rat _r1, Rat _r2, Rat _r3, Sword _sword, Question _q, Exclamation _ex) {
		gamecheck2(_p,_m,_r1, _r2, _r3, _sword, _q, _ex);
		if(_r1.getIsAlive1()) {
			_r1.move1(_z, _p);
		}
		if(_r2.getIsAlive2()) {
			_r2.move2(_z, _p);
		}
		if(_r3.getIsAlive3()) {
			_r3.move3(_z, _p);
		}
		if(_m.getIsAlive()) {
			_m.move(_z, _p);
		}
	}

	//removes the sword from the player
	public boolean swordRemove(Player _p, Sword _sword) {
		//checks if hasSword is true
	//has sword
		if(_p.hasSword(_sword)) {
			return true;
		}
		return false;
	}
	
	/*
	 * Checks if the player location is the same as enemy, teleport, or question position and execute actions correspondingly
	 * Also handles going to next level
	 */
	public void gamecheck1(Player _p, Maze _z, Minotaur _m, Rat _r1, Rat _r2, Rat _r3, Sword _sword, Teleport teleport, Question _q,
			Exclamation _ex) {//with teleport		
		if (_p.getPosition().getRow() == _m.getPosition().getRow()
				&& _p.getPosition().getCol() == _m.getPosition().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed LARRY!");
				_m.killMinotaur();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - LARRY killed you!");
				askRestart();
			}
		}
		if (_ex.getPosition().getRow() == _p.getPosition().getRow() 
				&& _ex.getPosition().getCol() == _p.getPosition().getCol()) {
			_ex.dead();
			_ex.setFalse1();
			setExTrue();
			Window.msg("You have triggered the block! In effect, the exit pipe has been revealed!");
	}
		
		if (_p.getPosition().getRow() == _r1.getPosition1().getRow() 
				&& _p.getPosition().getCol() == _r1.getPosition1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed LEMMY!");
				_r1.killRat1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - LEMMY killed you!");
				askRestart();
		}
	}
		
		if (_p.getPosition().getRow() == _r2.getPosition2().getRow() 
				&& _p.getPosition().getCol() == _r2.getPosition2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed KOOPA TROOPA!");
				_r2.killRat2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - KOOPA TROOPA killed you!");
				askRestart();
				}
		}
		
		if (_p.getPosition().getRow() == _r3.getPosition3().getRow() 
				&& _p.getPosition().getCol() == _r3.getPosition3().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed rat B!");
				_r3.killRat3();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died- KOOPA TROOPA killed you!");
				askRestart();
				}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(_p.getPosition().getRow() == _z.getEnd().getRow() && _p.getPosition().getCol() == _z.getEnd().getCol())
		{
			_p.killPlayer();
			Window.msg("Mario made it to the pipe!");	
			level++;
			Overseer.setLevelFalse();
				levelDone();
		}
		if(_p.getPosition().getRow() == _sword.getPosition().getRow()
				&& _p.getPosition().getCol() == _sword.getPosition().getCol()) {
			Window.msg("You obtained LINK's MASTER SWORD!");
			Window.msg2("By obtaining the MASTER SWORD, Mario has temporarily transformed into LINK!", lnksword);
			link = true;
			_p.hasSword(_sword);
		}
		if(_p.getPosition().getRow() == teleport.getPosition().getRow() && _p.getPosition().getCol() == teleport.getPosition().getCol()) {
			levelSelect();
		}
		
		if (null != _q.getQuestPos1() &&_p.getPosition().getRow() == _q.getQuestPos1().getRow() && _p.getPosition().getCol() == _q.getQuestPos1().getCol()) {
            if(_q.randomQuestion1()) { 
            	cnt+=20;
            	_q.killQues(1);
            }else{
            	cnt-=10;
            	 _q.killQues(1);
            }
        }
        if (null != _q.getQuestPos2() &&_p.getPosition().getRow() == _q.getQuestPos2().getRow() && _p.getPosition().getCol() == _q.getQuestPos2().getCol()) {
            if(_q.randomQuestion2()) {
            	cnt+=20;
                 	_q.killQues(2);
                 }else{
                	cnt-=10;
                 	 _q.killQues(2);
                 }
            }
        if (null != _q.getQuestPos3() &&_p.getPosition().getRow() == _q.getQuestPos3().getRow() && _p.getPosition().getCol() == _q.getQuestPos3().getCol()) {
        	if(_q.randomQuestion3()) {
        		cnt+=20;
                	_q.killQues(3);
                }else{
                	cnt-=10;
                	 _q.killQues(3);
                }
           }
        if (null != _q.getQuestPos4() &&_p.getPosition().getRow() == _q.getQuestPos4().getRow() && _p.getPosition().getCol() == _q.getQuestPos4().getCol()) {
        	 if(_q.randomQuestion4()) {
        		 cnt += 20;
             	_q.killQues(4);
             }
             else {
            	 cnt -= 10;
             	_q.killQues(4);
             }   
        }
        if (null != _q.getQuestPos5() &&_p.getPosition().getRow() == _q.getQuestPos5().getRow() && _p.getPosition().getCol() == _q.getQuestPos5().getCol()) {
            if(_q.randomQuestion5()) {
            	cnt += 20;
            	_q.killQues(5);
            }
            else {
            	cnt -= 10;
            	_q.killQues(5);
            }
        }
	}
	
	//Handles the same thing as gamecheck1 but only if player comes in contact with enemies or questions
	public void gamecheck2(Player _p, Minotaur _m, Rat _r1, Rat _r2, Rat _r3, Sword _sword, Question _q, Exclamation _ex) {
		if (_p.getPosition().getRow() == _m.getPosition().getRow()
				&& _p.getPosition().getCol() == _m.getPosition().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed LARRY!");
				_m.killMinotaur();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - LARRY killed you!");
				askRestart();
			}
		}
		
		if (_p.getPosition().getRow() == _r1.getPosition1().getRow() 
				&& _p.getPosition().getCol() == _r1.getPosition1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed LEMMY!");
				_r1.killRat1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - LEMMY killed you!");
				askRestart();
		}
	}
		
		if (_p.getPosition().getRow() == _r2.getPosition2().getRow() 
				&& _p.getPosition().getCol() == _r2.getPosition2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed KOOPA TROOPA!");
				_r2.killRat2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - KOOPA TROOPA killed you!");
 
				askRestart();
				}
		}
		
		if (_p.getPosition().getRow() == _r3.getPosition3().getRow() 
				&& _p.getPosition().getCol() == _r3.getPosition3().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed KOOPA TROOPA!");
				_r3.killRat3();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - KOOPA TROOPA killed you!");
				askRestart();
				}
		if (null != _q.getQuestPos1() &&_p.getPosition().getRow() == _q.getQuestPos1().getRow() && _p.getPosition().getCol() == _q.getQuestPos1().getCol()) {
            if(_q.randomQuestion1()) { 
            	cnt+=20;
            	_q.killQues(1);
            }else{
            	cnt-=10;
            	 _q.killQues(1);
            }
        }
        if (null != _q.getQuestPos2() &&_p.getPosition().getRow() == _q.getQuestPos2().getRow() && _p.getPosition().getCol() == _q.getQuestPos2().getCol()) {
            if(_q.randomQuestion2()) {
            	cnt+=20;
                 	_q.killQues(2);
                 }else{
                	 cnt-=10;
                 	 _q.killQues(2);
                 }
            }
        if (null != _q.getQuestPos3() &&_p.getPosition().getRow() == _q.getQuestPos3().getRow() && _p.getPosition().getCol() == _q.getQuestPos3().getCol()) {
        	if(_q.randomQuestion3()) {
        		cnt+=20;
                	_q.killQues(3);
                }else{
                	cnt-=10;
                	 _q.killQues(3);
                }
           }
        if (null != _q.getQuestPos4() &&_p.getPosition().getRow() == _q.getQuestPos4().getRow() && _p.getPosition().getCol() == _q.getQuestPos4().getCol()) {
        	 if(_q.randomQuestion4()) {
        		 cnt += 20;
             	_q.killQues(4);
             }
             else {
            	 cnt -= 10;
             	_q.killQues(4);
             }   
        }
        if (null != _q.getQuestPos5() &&_p.getPosition().getRow() == _q.getQuestPos5().getRow() && _p.getPosition().getCol() == _q.getQuestPos5().getCol()) {
            if(_q.randomQuestion5()) {
            	cnt += 20;
            	_q.killQues(5);
            }
            else {
            	cnt -= 10;
            	_q.killQues(5);
            }
        }
	}
	}

	/*
	 * Sends Mario accordingly to the world selected
	 */
	public void levelSelect() {
		int x = Window.option1(WORLDS, "You now have obtained a world teleporter, choose a WORLD!"
				+ "\nYou can continue to proceed throughout the whole game by choosing the next world*"
				+ "\n*Levels proceed as normal after the selection.", lvlSelect);
		if(x==0) {//level 2
			Overseer.setLevelFalse();
			Window.msg2("Launching Mario to World 2!", cannon2);
			Window.msg2("Entering World 2!"
					+ "\nCAUTION: Mario only has 40 turns and gains 20 turns or loses 10 turns from Question Blocks!", world2);
			Overseer2 o = new Overseer2();
			Overseer2.STAGE2();		
			}
		if(x==1) {//level 3
			Overseer.setLevelFalse();
			Window.msg2("Launching Mario to World 3!", cannon3);
			Window.msg2("Entering World 3!"
					+ "\nCAUTION: Mario only has 50 turns and gains 20 turns or loses 15 turns from Question Blocks!", world3);
			Overseer3 o = new Overseer3();
			Overseer3.STAGE3();
		}
		if(x==2) {//level 4
			Overseer.setLevelFalse();
			Window.msg2("Launching Mario to World 4!", cannon4);
			Window.msg2("Entering World 4!"
					+ "\nCAUTION: Mario only has 70 turns and gains 20 turns or loses 15 turn from Question Blocks!", world4);
			Overseer4 o = new Overseer4();
			home();
			Overseer4.STAGE4();
		}
		if(x==3) {//level 5
			Overseer.setLevelFalse();
			Window.msg2("Launching Mario to World 5!", cannon5);
			Window.msg2("Entering World 5!"
					+ "\nCAUTION: Mario only has 50 turns and gains 30 turns or loses 25 turns from Question Blocks!", world5);
			Overseer5 o = new Overseer5();
			Overseer5.STAGE5();
		}
		if(x==4 && cnt<=0) {//Lost, not enough steps; This is done to prevent breaking the game by moving back and forth between the teleporter
			Overseer.setLevelFalse();
			Window.msg2("Mario is too tired and cannot move anymore!*\nBowser has won, keeping Peach forever..."
					+ "\n*0 turns after moving to Teleporter", gameOver );
			askRestart();
		}
		
	}
	
	//Transitions the player from World 1 to World 2
	public void levelDone() {
		Overseer2 o = new Overseer2();
		Window.msg2("Entering World 2!"
				+ "\nCAUTION: Mario only has 40 turns and gains 20 turns or loses 10 turns from Question Blocks!", world2);
		Overseer2.STAGE2();
	}	
	
	//If the player loses before completing anything, method asks player if they want to restart again
	public void askRestart() {
		int x = Window.option1(RESTART, "What Now?", think);
		if(x==0) {
			Window.msg2("Awesome, another try to save Princess Peach!", go);
			Overseer.setLevelFalse();
			Run.RESTART();
			OverseerRestart.setRestart();

		}
		if(x==1) {
		Window.msg2("Sending Mario back to Mushroom Kingdom...", pipe);
			System.exit(0);
		}
	}
}
			
	
