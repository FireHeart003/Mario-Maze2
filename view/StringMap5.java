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
import Maze.mod.Player2;

public class StringMap5 extends JFrame{
	/*
	 * World 5
	 * Declare and initialize all private instance variables
	 */
	private static final long serialVersionUID = 1L;

	private JPanel _map = new JPanel(new GridLayout(21,21));
	
	private JLabel[][] Maze = new JLabel[21][21];
	
	int cnt = 49;

	private Maze _z;
	private static final String[] WORLDS = {"World 2", "World 3", "World 4", "World 5"};

	//everything 28x28
	//wall and player 30x30
	private Icon player = new ImageIcon(getClass().getResource("mar.png"));
	private Icon floor = new ImageIcon(getClass().getResource("lava floor.png"));
	private Icon wall = new ImageIcon(getClass().getResource("lava wall.png"));
	private Icon minotaur = new ImageIcon(getClass().getResource("dry icon.png"));
	private Icon exit = new ImageIcon(getClass().getResource("peach.png"));
	private Icon rat1 = new ImageIcon(getClass().getResource("dry bones.png"));
	private Icon cact = new ImageIcon(getClass().getResource("dry bones.png"));
	private Icon rat2 = new ImageIcon(getClass().getResource("dry bones.png"));
	private Icon sword = new ImageIcon(getClass().getResource("link.png"));
	private Icon quest = new ImageIcon(getClass().getResource("quest.png"));
	private Icon gameOver = new ImageIcon(getClass().getResource("bows.png"));
	private static final String[] RESTART = {"Restart GAME!", "Exit"};
	private ImageIcon think =  new ImageIcon((getClass().getResource("think.png")));
	private Icon go = new ImageIcon(getClass().getResource("lets go.png"));
	private Icon pipe = new ImageIcon(getClass().getResource("peach.png"));
	private ImageIcon mar =  new ImageIcon((getClass().getResource("mar.jpg")));
	private Icon end = new ImageIcon(getClass().getResource("end.png"));
	private Icon endBows = new ImageIcon(getClass().getResource("endbows.png"));
	private ImageIcon lava =  new ImageIcon((getClass().getResource("lava.png")));
	private Icon ex = new ImageIcon(getClass().getResource("ex.png"));
	private Icon key = new ImageIcon(getClass().getResource("key.png"));
	private Icon door = new ImageIcon(getClass().getResource("door.png"));
	boolean lock = true;
	
	private Icon lnksword = new ImageIcon(getClass().getResource("sword.png"));
	boolean link = false;	
	private ImageIcon lnk =  new ImageIcon((getClass().getResource("link icon.png")));

	boolean onEx = false;//For exclamation block
	
	//Ending message that ends the game
	public void end() {
		Window.msg2("Awww man, in the end, through all that effort, Mario was not able to reach the end.", mar);
		System.exit(0);
	}
	
	//Asks player if they want to restart
	public void restart() {
		if(OverseerRestart.restart()== false) {
			askRestart();
		}
		else {
			end();
		}
	}

	//Updates the top part of the game where the number of steps a player can take is updated on the top left corner
	public void updateCnt() {
		String s = "Number of Turns Left: "+ getCnt();
		Border border = BorderFactory.createTitledBorder(s);
		_map.setBorder(border);
 }

	//Getters
	public JPanel getMap(){	return _map; }
	public int getCnt() { return cnt; }
	public boolean getEx() { return onEx; }
	
	//Setter
	public void setExTrue() {
		onEx = true;
	}
	
	//Asks player if they want to restart
	public void askRestart() {
		int x = Window.option1(RESTART, "What Now?", think);
		if(x==0) {
			Window.msg2("Awesome, another try to save Princess Peach!", go);
			OverseerRestart.setRestart();
			Overseer5.setLevel5False();
			Run.RESTART();
		}
		if(x==1) {
		Window.msg2("Sending Mario back to Mushroom Kingdom...", pipe);
			System.exit(0);
		}
	}
	
	//Updates all inputs and handles all movements in the game
	public JPanel updateMap(Player2 _p, Maze z, String userIn, Minotaur2 m, Rat2 r1, Rat2 r2, Sword s,
			Lava l1, Lava l2, Lava l3, Lava l4, Lava l5, Lava l6, Lava l7, Question3 _q, Exclamation _ex
			, Key _k, Lock d1, Lock d2, Enemy dry) {
		_map = new JPanel(new GridLayout(21,21));
			
		if (userIn.equals("NORTH")) {
			updateCnt();
				 if (z.getMaze()[_p.getPosition().getRow()-1][_p.getPosition().getCol()] == false) {
						if(_p.getPosition().getRow() == d2.getLock2().getRow() && _p.getPosition().getCol() == d2.getLock2().getCol()) {
					updateCnt();
					if(lock) {
					Window.msg("UNLOCK the door to move forward!");	
						}}
					else {
						updateCnt();
		            if (z.getMaze()[_p.getPosition().getRow()-1][_p.getPosition().getCol()] == false) {
		                _p.move(Direction.UP, z);
		                move(_p, z, m, r1, r2, dry, s, _q);
		                gamecheck1(_p, z, m, r1, r2, dry,l1,l2,l3,l4,l5,l6,l7, s, _q, _ex, _k, d1, d2);
		                cnt--;
		                if(cnt<-1) {
		                	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
		                	restart();
		        			} 
            			}
					}
            	}
            } 
		else if (userIn.equals("SOUTH")) {
			updateCnt();
			 if (z.getMaze()[_p.getPosition().getRow()+1][_p.getPosition().getCol()] == false) {
					if(_p.getPosition().getRow() == d1.getLock1().getRow() && _p.getPosition().getCol() == d1.getLock1().getCol()) {
				updateCnt();
				if(lock) {
				Window.msg("UNLOCK the door to move forward!");	
					}}
				else {
					updateCnt();
		            if (z.getMaze()[_p.getPosition().getRow()+1][_p.getPosition().getCol()] == false) {
		                _p.move(Direction.DOWN, z);
		                move(_p, z, m, r1, r2, dry, s, _q);
		                gamecheck1(_p, z, m, r1, r2, dry,l1,l2,l3,l4,l5,l6,l7, s, _q, _ex, _k, d1, d2);
		                cnt--;
		                if(cnt<-1) {
		                	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
		                	restart();
		        			} 
		            	}
		            } 
			 }
       } 
  
		else if (userIn.equals("EAST")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()][_p.getPosition().getCol()+1] == false) {
               _p.move(Direction.RIGHT, z);
               move(_p, z, m, r1, r2, dry, s, _q);
               gamecheck1(_p, z, m, r1, r2, dry,l1,l2,l3,l4,l5,l6,l7, s, _q, _ex, _k, d1, d2);
               cnt--;
               if(cnt<-1) {
               	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
               	restart();
       			} 
            } 
        }
		else if (userIn.equals("WEST")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()][_p.getPosition().getCol()-1] == false) {
                _p.move(Direction.LEFT, z);
                move(_p, z, m, r1, r2, dry, s, _q);
                gamecheck1(_p, z, m, r1, r2, dry,l1,l2,l3,l4,l5,l6,l7, s, _q, _ex, _k, d1, d2);
                cnt--;
                if(cnt<-1) {
                	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
                	restart();
        			} 
              /*  if (z.getMaze()[p.getPosition().getRow()][p.getPosition().getCol()-1] == true) {
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
				else if(r == r2.getPosition2().getRow() &&
						c == r2.getPosition2().getCol()) {
					Maze[r][c] = new JLabel(rat2);
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
				else if(r == l1.getLava1().getRow() &&
						c == l1.getLava1().getCol()) {
					Maze[r][c] = new JLabel(lava);
					_map.add(Maze[r][c]);
				}else if(r == l2.getLava2().getRow() &&
						c == l2.getLava2().getCol()) {
					Maze[r][c] = new JLabel(lava);
					_map.add(Maze[r][c]);
				}else if(r == l3.getLava3().getRow() &&
						c == l3.getLava3().getCol()) {
					Maze[r][c] = new JLabel(lava);
					_map.add(Maze[r][c]);
				}else if(r == l4.getLava4().getRow() &&
						c == l4.getLava4().getCol()) {
					Maze[r][c] = new JLabel(lava);
					_map.add(Maze[r][c]);
				}else if(r == l5.getLava5().getRow() &&
						c == l5.getLava5().getCol()) {
					Maze[r][c] = new JLabel(lava);
					_map.add(Maze[r][c]);
				}else if(r == l6.getLava6().getRow() &&
						c == l6.getLava6().getCol()) {
					Maze[r][c] = new JLabel(lava);
					_map.add(Maze[r][c]);
				}else if(r == l7.getLava7().getRow() &&
						c == l7.getLava7().getCol()) {
					Maze[r][c] = new JLabel(lava);
					_map.add(Maze[r][c]);
				}
				else if(r == m.getPosition().getRow() &&
						c == m.getPosition().getCol()) {
					Maze[r][c] = new JLabel(minotaur);
					_map.add(Maze[r][c]);	
				}
				else if(r == dry.getCactus1().getRow() &&
						c == dry.getCactus1().getCol()) {
					Maze[r][c] = new JLabel(cact);
					_map.add(Maze[r][c]);
				}
				else if(r == _ex.getPosition().getRow() &&
						c == _ex.getPosition().getCol()) {
					Maze[r][c] = new JLabel(ex);
					_map.add(Maze[r][c]);						
				}
				else if(r == r1.getPosition1().getRow() &&
						c == r1.getPosition1().getCol()) {
					Maze[r][c] = new JLabel(rat1);
					_map.add(Maze[r][c]);
				}
				else if(r == s.getPosition().getRow() &&
						c == s.getPosition().getCol()) {
					Maze[r][c] = new JLabel(sword);
					_map.add(Maze[r][c]);						
				}
				else if(r == _k.getKey().getRow() &&
						c == _k.getKey().getCol()) {
					Maze[r][c] = new JLabel(key);
					_map.add(Maze[r][c]);						
				}
				else if(r == d1.getLock1().getRow() &&
						c == d1.getLock1().getCol()) {
					Maze[r][c] = new JLabel(door);
					_map.add(Maze[r][c]);						
				}
				else if(r == d2.getLock2().getRow() &&
						c == d2.getLock2().getCol()) {
					Maze[r][c] = new JLabel(door);
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
	public void move(Player2 _p, Maze _z, Minotaur2 _m, Rat2 _r1, Rat2 _r2, Enemy dry, Sword _sword, Question3 _q) {
		gamecheck2(_p,_m,_r1, _r2, dry, _sword, _q);
		if(_r1.getIsAlive1()) {
			_r1.move1(_z, _p);
		}
		if(_r2.getIsAlive2()) {
			_r2.move2(_z, _p);
		}
		if(dry.getIsAlive1()) {
			dry.move2(_z);
		}
		if(_m.getIsAlive()) {
			_m.move(_z, _p);
		}
	}

	//removes the sword from the player
	public boolean swordRemove(Player2 _p, Sword _sword) {
	//	checks if hasSword is true
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
	public void gamecheck1(Player2 _p, Maze _z, Minotaur2 _m, Rat2 _r1, Rat2 _r2, Enemy dry,
			Lava l1, Lava l2, Lava l3, Lava l4, Lava l5, Lava l6, Lava l7,Sword _sword, Question3 _q, Exclamation _ex, 
			Key _k, Lock d1, Lock d2) {//with teleport		
		
		if (_p.getPosition().getRow() == _m.getPosition().getRow()
				&& _p.getPosition().getCol() == _m.getPosition().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed DRY BOWSER!");
				_m.killMinotaur();
				_p.hasSword1(_sword);
				link = false;
 				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer2();
				Window.msg("Mario died - DRY BOWSER killed you!");
				restart();
			}
		}
		if (_p.getPosition().getRow() == dry.getCactus1().getRow() 
				&& _p.getPosition().getCol() == dry.getCactus1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed DRY BONES!");
				dry.killCactus1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer2();
				Window.msg("Mario died - DRY BONES killed you!");
				restart();		}
	}
		if (_ex.getPosition().getRow() == _p.getPosition().getRow() 
				&& _ex.getPosition().getCol() == _p.getPosition().getCol()) {
			_ex.dead();
			_ex.setFalse1();
			setExTrue();
			l1.dead1();
			l1.setFalse1();
			l2.dead2();
			l2.setFalse2();
			l3.dead3();
			l3.setFalse3();
			l4.dead4();
			l4.setFalse4();
			l5.dead5();
			l5.setFalse5();
			l6.dead6();
			l6.setFalse6();
			l7.dead7();
			l7.setFalse7();
			setExTrue();
			Window.msg("You have triggered the block! In effect, the lava has settled down!"
					+ "\n PEACH'S prison cell has been revealed!");
	}
		if (_p.getPosition().getRow() == l1.getLava1().getRow()
				&& _p.getPosition().getCol() == l1.getLava1().getCol()) {
				_p.killPlayer2();
				Window.msg("Mario died - Burned by LAVA!");
				restart();
			}
		if (_p.getPosition().getRow() == l2.getLava2().getRow()
				&& _p.getPosition().getCol() == l2.getLava2().getCol()) {
				_p.killPlayer2();
				Window.msg("Mario died - Burned by LAVA!");
				restart();
			}
		if (_p.getPosition().getRow() == l3.getLava3().getRow()
					&& _p.getPosition().getCol() == l3.getLava3().getCol()) {
					_p.killPlayer2();
					Window.msg("Mario died - Burned by LAVA!");
					restart();
				}
		if (_p.getPosition().getRow() == l4.getLava4().getRow()
						&& _p.getPosition().getCol() == l4.getLava4().getCol()) {
					_p.killPlayer2();
					Window.msg("Mario died - Burned by LAVA!");
					restart();
				}
		if (_p.getPosition().getRow() == l5.getLava5().getRow()
						&& _p.getPosition().getCol() == l5.getLava5().getCol()) {
					_p.killPlayer2();
					Window.msg("Mario died - Burned by LAVA!");
					restart();
				}
		if (_p.getPosition().getRow() == l6.getLava6().getRow()
						&& _p.getPosition().getCol() == l6.getLava6().getCol()) {
					_p.killPlayer2();
					Window.msg("Mario died - Burned by LAVA!");
					restart();
				}
		if (_p.getPosition().getRow() == l7.getLava7().getRow()
						&& _p.getPosition().getCol() == l7.getLava7().getCol()) {
					_p.killPlayer2();
					Window.msg("Mario died - Burned by LAVA!");
					restart();
				}
		
		if (_p.getPosition().getRow() == _r1.getPosition1().getRow() 
				&& _p.getPosition().getCol() == _r1.getPosition1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed DRY BONES!");
				_r1.killRat1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer2();
				Window.msg("Mario died - DRY BONES killed you!");
				restart();
		}
	}
		
		if (_p.getPosition().getRow() == _r2.getPosition2().getRow() 
				&& _p.getPosition().getCol() == _r2.getPosition2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed DRY BONES!");
				_r2.killRat2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer2();
				Window.msg("Mario died - DRY BONES killed you!");
				restart();
			}
		}
		
		/////////////////////////////
		if(_p.getPosition().getRow() == _z.getEnd().getRow() && _p.getPosition().getCol() == _z.getEnd().getCol())
		{
			_p.killPlayer2();
			Window.msg2("Mario saved Princess Peach from Bowser and all his atrocities!", end);	
			cnt++;
			Overseer5.setLevel5False();
			levelDone();
			
		}
		if(_p.getPosition().getRow() == _sword.getPosition().getRow()
				&& _p.getPosition().getCol() == _sword.getPosition().getCol()) {
			Window.msg("You obtained LINK's MASTER SWORD!");
			Window.msg2("By obtaining the MASTER SWORD, Mario has temporarily transformed into LINK!", lnksword);
			link = true;
			_p.hasSword(_sword);
		}
		if(_p.getPosition().getRow() == _k.getKey().getRow()
				&& _p.getPosition().getCol() == _k.getKey().getCol()) {
			Window.msg("MARIO got the KEY!");
			_p.hasKey(_k);
			lock = false;
		}
		if(_p.getPosition().getRow() == d1.getLock1().getRow()
				&& _p.getPosition().getCol() == d1.getLock1().getCol() ||
				_p.getPosition().getRow() == d2.getLock2().getRow()
				&& _p.getPosition().getCol() == d2.getLock2().getCol()) {
			if(_p.giveKey()) {
				Window.msg("The key you obtained has been used to unlock both DOORS!");
				d1.killLock1();
				d2.killLock2();
				_p.hasKey1(_k);
				Window.msg("MARIO'S key has faded away - MARIO used it!");
			}
			else {
				Window.msg("You need the key to unlock the door!");
				
			}
		}
		
		
		if (null != _q.getQuestPos1() &&_p.getPosition().getRow() == _q.getQuestPos1().getRow() && _p.getPosition().getCol() == _q.getQuestPos1().getCol()) {
            if(_q.randomQuestion1()) { 
            	cnt+=30;
            	_q.killQues(1);
            }else{
            	cnt-=25;
            	 _q.killQues(1);
            }
        }
        if (null != _q.getQuestPos2() &&_p.getPosition().getRow() == _q.getQuestPos2().getRow() && _p.getPosition().getCol() == _q.getQuestPos2().getCol()) {
            if(_q.randomQuestion2()) {
            	cnt+=30;
                 	_q.killQues(2);
                 }else{
                	 	cnt-=25;
                 	 _q.killQues(2);
                 }
            }
        if (null != _q.getQuestPos3() &&_p.getPosition().getRow() == _q.getQuestPos3().getRow() && _p.getPosition().getCol() == _q.getQuestPos3().getCol()) {
        	if(_q.randomQuestion3()) {
        		cnt+=30;
                	_q.killQues(3);
                }else{
                	cnt-=25;
                	 _q.killQues(3);
                }
           }
        if (null != _q.getQuestPos4() &&_p.getPosition().getRow() == _q.getQuestPos4().getRow() && _p.getPosition().getCol() == _q.getQuestPos4().getCol()) {
        	 if(_q.randomQuestion4()) {
        		 cnt += 30;
             	_q.killQues(4);
             }
             else {
            	 cnt -= 25;
             	_q.killQues(4);
             }   
        }
        if (null != _q.getQuestPos5() &&_p.getPosition().getRow() == _q.getQuestPos5().getRow() && _p.getPosition().getCol() == _q.getQuestPos5().getCol()) {
            if(_q.randomQuestion5()) {
            	cnt += 30;
            	_q.killQues(5);
            }
            else {
            	cnt -= 25;
            	_q.killQues(5);
            }
        }
	}
	
	//Handles the same thing as gamecheck1 but only if player comes in contact with enemies or questions
	public void gamecheck2(Player2 _p, Minotaur2 _m, Rat2 _r1, Rat2 _r2, Enemy dry, Sword _sword, Question3 _q) {
		if (_p.getPosition().getRow() == _m.getPosition().getRow()
				&& _p.getPosition().getCol() == _m.getPosition().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed DRY BOWSER!");
				_m.killMinotaur();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer2();
				Window.msg("Mario died - DRY BOWSER killed you!");
				restart();
			}
		}
		if (_p.getPosition().getRow() == dry.getCactus1().getRow() 
				&& _p.getPosition().getCol() == dry.getCactus1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed DRY BONES!");
				dry.killCactus1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer2();
				Window.msg("Mario died - DRY BONES killed you!");
				restart();		}
	}
		if (_p.getPosition().getRow() == _r1.getPosition1().getRow() 
				&& _p.getPosition().getCol() == _r1.getPosition1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed DRY BONES!");
				_r1.killRat1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer2();
				Window.msg("Mario died - DRY BONES killed you!");
				restart();
		}
	}
		
		if (_p.getPosition().getRow() == _r2.getPosition2().getRow() 
				&& _p.getPosition().getCol() == _r2.getPosition2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed DRY BONES!");
				_r2.killRat2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer2();
				Window.msg("Mario died - DRY BONES killed you!");
				restart();
			}
		}
		if (null != _q.getQuestPos1() &&_p.getPosition().getRow() == _q.getQuestPos1().getRow() && _p.getPosition().getCol() == _q.getQuestPos1().getCol()) {
            if(_q.randomQuestion1()) { 
            	cnt+=30;
            	_q.killQues(1);
            }else{
            	cnt-=25;
            	 _q.killQues(1);
            }
        }
        if (null != _q.getQuestPos2() &&_p.getPosition().getRow() == _q.getQuestPos2().getRow() && _p.getPosition().getCol() == _q.getQuestPos2().getCol()) {
            if(_q.randomQuestion2()) {
            	cnt+=30;
                 	_q.killQues(2);
                 }else{
                	 cnt-=25;
                 	 _q.killQues(2);
                 }
            }
        if (null != _q.getQuestPos3() &&_p.getPosition().getRow() == _q.getQuestPos3().getRow() && _p.getPosition().getCol() == _q.getQuestPos3().getCol()) {
        	if(_q.randomQuestion3()) {
        		cnt+=30;
                	_q.killQues(3);
                }else{
                	cnt-=25;
                	 _q.killQues(3);
                }
           }
        if (null != _q.getQuestPos4() &&_p.getPosition().getRow() == _q.getQuestPos4().getRow() && _p.getPosition().getCol() == _q.getQuestPos4().getCol()) {
        	 if(_q.randomQuestion4()) {
        		 cnt += 30;
             	_q.killQues(4);
             }
             else {
            	 cnt -= 25;
             	_q.killQues(4);
             }   
        }
        if (null != _q.getQuestPos5() &&_p.getPosition().getRow() == _q.getQuestPos5().getRow() && _p.getPosition().getCol() == _q.getQuestPos5().getCol()) {
            if(_q.randomQuestion5()) {
            	cnt += 30;
            	_q.killQues(5);
            }
            else {
            	cnt -= 25;
            	_q.killQues(5);
            }
        }
	}
	//Transitions the player from World 5 to the ENDING
	public void levelDone() {
		Window.msg2("Mario: OOF, Bowser, eat my dust!"
				+ "\n Bowser: NOOOOOOOOOO"
				+ "\n Koopa Kids: NOOOOOOOO"
				+ "\n Peach: Bye!", endBows);
	}
}
