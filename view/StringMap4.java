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

public class StringMap4 extends JFrame{
	/*
	 * World 4
	 * Declare and initialize all private instance variables
	 */
	private static final long serialVersionUID = 1L;

	private JPanel _map = new JPanel(new GridLayout(21,21));
	
	private JLabel[][] Maze = new JLabel[21][21];

	public JPanel getMap(){ return _map; }
	int cnt = 69;
	//+20,-15

	private Maze _z;
	private static final String[] WORLDS = {"World 2", "World 3", "World 4", "World 5"};
	private int boo = 0;//counter used to make it so that every other step player takes, the ghosts dissapear
	
	//everything 28x28
	//wall and player 30x30
	//Images used for GUI aspect of game
	private Icon player = new ImageIcon(getClass().getResource("mar.png"));
	private Icon floor = new ImageIcon(getClass().getResource("star.png"));
	private Icon wall = new ImageIcon(getClass().getResource("note.png"));
	private Icon minotaur = new ImageIcon(getClass().getResource("jr.png"));
	private Icon exit = new ImageIcon(getClass().getResource("red.png"));
	private Icon rat1 = new ImageIcon(getClass().getResource("kingboo.png"));
	private Icon rat2 = new ImageIcon(getClass().getResource("boo.png"));
	private Icon rat3 = new ImageIcon(getClass().getResource("boo.png"));
	private Icon cact = new ImageIcon(getClass().getResource("bomb.png"));
	private Icon sword = new ImageIcon(getClass().getResource("link.png"));
	private Icon quest = new ImageIcon(getClass().getResource("world4quest.png"));
	private Icon gameOver = new ImageIcon(getClass().getResource("bows.png"));
	private ImageIcon world5 =  new ImageIcon((getClass().getResource("world5.png")));
	private static final String[] RESTART = {"Restart GAME!", "Exit"};
	private ImageIcon think =  new ImageIcon((getClass().getResource("think.png")));
	private Icon go = new ImageIcon(getClass().getResource("lets go.png"));
	private Icon pipe = new ImageIcon(getClass().getResource("pip.png"));
	private ImageIcon mar =  new ImageIcon((getClass().getResource("mar.jpg")));
	private Icon ex1 = new ImageIcon(getClass().getResource("ex.png"));
	private Icon floor1 = new ImageIcon(getClass().getResource("black.png"));
	private Icon wall2 = new ImageIcon(getClass().getResource("wall2.jpg"));
	private Icon lnksword = new ImageIcon(getClass().getResource("sword.png"));
	boolean link = false;	
	private ImageIcon lnk =  new ImageIcon((getClass().getResource("link icon.png")));
	
	//For exclamation block
	boolean onEx = false;

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
	
	//Setter
	public void setExTrue() {
		onEx = true;
	}
	
	//Getters
	public int getCnt() { return cnt;}
	public boolean getEx() { return onEx;}

	//Updates the top part of the game where the number of steps a player can take is updated on the top left corner
	public void updateCnt() {
		String s = "Number of Turns Left: "+ getCnt();
		Border border = BorderFactory.createTitledBorder(s);
		_map.setBorder(border);
	}
	
	//Asks player if they want to restart
	public void askRestart() {
		int x = Window.option1(RESTART, "What Now?", think);
		if(x==0) {
			Window.msg2("Awesome, another try to save Princess Peach!", go);
			OverseerRestart.setRestart();
			Overseer4.setLevel4False();
			Run.RESTART();
		}
		if(x==1) {
		Window.msg2("Sending Mario back to Mushroom Kingdom...", pipe);
			System.exit(0);
		}
	}
	
	//Updates all inputs and handles all movements in the game
	public JPanel updateMap(Player _p, Maze z, String userIn, Minotaur m, Rat r1, Rat r2, Rat r3, Sword s, Question2 _q,Enemy cact1, Exclamation ex) {
		_map = new JPanel(new GridLayout(21,21));
			
		if (userIn.equals("NORTH")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()-1][_p.getPosition().getCol()] == false) {
                _p.move(Direction.UP, z);
                move(_p, z, m, r1, r2, r3, s, _q, cact1, ex);
                gamecheck1(_p, z, m, r1, r2, r3, s, _q,cact1,  ex);   
                cnt--;
                boo++;
                if(cnt<-1) {
                	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
                	restart();
        			} 
            } 
        }
		else if (userIn.equals("SOUTH")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()+1][_p.getPosition().getCol()] == false) {
                _p.move(Direction.DOWN, z);
                move(_p, z, m, r1, r2, r3, s, _q,cact1, ex);
                gamecheck1(_p, z, m, r1, r2, r3, s, _q,cact1, ex);  
                cnt--;
                boo++;
                if(cnt<-1) {
                	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
                	restart();
        			} 
            } 
        }
		else if (userIn.equals("EAST")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()][_p.getPosition().getCol()+1] == false) {
               _p.move(Direction.RIGHT, z);
               move(_p, z, m, r1, r2, r3, s, _q,cact1, ex);
               gamecheck1(_p, z, m, r1, r2, r3, s, _q,cact1, ex);
               cnt--;
               boo++;
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
                move(_p, z, m, r1, r2, r3, s, _q,cact1, ex);
                gamecheck1(_p, z, m, r1, r2, r3, s, _q, cact1, ex);
                cnt--;
                boo++;
                if(cnt<-1) {
                	Window.msg2("Mario is too tired and cannot move anymore! \nBowser has won, keeping Peach forever...", gameOver);
                	restart();
        			} 
            } 
        }
		
		//GUI where we add each imageicon corresponding to the character/object
		for(int r = 0; r < z.getMaze().length; r++) {
			for(int c = 0; c < z.getMaze()[r].length; c++) {
				if(z.getMaze()[r][c]) {
					if(getEx()) {
					Maze[r][c] = new JLabel(wall);
					_map.add(Maze[r][c]);	
					}
					else {
						Maze[r][c] = new JLabel(wall2);
						_map.add(Maze[r][c]);
					}
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
				else if(r == cact1.getCactus1().getRow() &&
						c == cact1.getCactus1().getCol()) {
					Maze[r][c] = new JLabel(cact);
					_map.add(Maze[r][c]);
				}
				else if(r == m.getPosition().getRow() &&
						c == m.getPosition().getCol()) {
					Maze[r][c] = new JLabel(minotaur);
					_map.add(Maze[r][c]);	
				}
				//r1-r3 implements the Boos disappearing and reappearing
				else if(r == r1.getPosition1().getRow() &&
						c == r1.getPosition1().getCol()) {
					if(boo%3==0 || getEx()) {
						Maze[r][c] = new JLabel(rat1);
					_map.add(Maze[r][c]);
					}
					else {
						if(getEx() == false) {
							Maze[r][c] = new JLabel(floor1);
							_map.add(Maze[r][c]);
						}
						else {
						Maze[r][c] = new JLabel(floor);
						_map.add(Maze[r][c]);
						}	
					}
				}
				else if(r == r2.getPosition2().getRow() &&
						c == r2.getPosition2().getCol()) {
					if(boo%3==0 || getEx()) {
					Maze[r][c] = new JLabel(rat2);
					_map.add(Maze[r][c]);
					}
					else {
						if(getEx() == false) {
							Maze[r][c] = new JLabel(floor1);
							_map.add(Maze[r][c]);
						}
						else {
						Maze[r][c] = new JLabel(floor);
						_map.add(Maze[r][c]);
						}	
					}
				}
				else if(r == r3.getPosition3().getRow() &&
						c == r3.getPosition3().getCol()) {
					if(boo%3==0 || getEx()) {
					Maze[r][c] = new JLabel(rat3);
					_map.add(Maze[r][c]);
					}	
					else {
						if(getEx() == false) {
							Maze[r][c] = new JLabel(floor1);
							_map.add(Maze[r][c]);
						}
						else {
						Maze[r][c] = new JLabel(floor);
						_map.add(Maze[r][c]);
						}
					}
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
				else if(r == ex.getPosition().getRow() &&
						c == ex.getPosition().getCol()) {
					Maze[r][c] = new JLabel(ex1);
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
					Maze[r][c] = new JLabel(exit);
					_map.add(Maze[r][c]);	
				}
				else {
					if(getEx() == false) {
						Maze[r][c] = new JLabel(floor1);
						_map.add(Maze[r][c]);
					}
					else {
					Maze[r][c] = new JLabel(floor);
					_map.add(Maze[r][c]);
					}
				}
				
			}
		}
		return _map;
	}
	
	//Checks if enemies are alive to allow them to move
	public void move(Player _p, Maze _z, Minotaur _m, Rat _r1, Rat _r2, Rat _r3, Sword _sword, Question2 _q, Enemy cast, Exclamation _ex) {
		gamecheck2(_p,_m,_r1, _r2, _r3, _sword, _q, cast, _ex);
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
		if(cast.getIsAlive1()) {
			cast.move1(_z);
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
	public void gamecheck1(Player _p, Maze _z, Minotaur _m, Rat _r1, Rat _r2, Rat _r3, Sword _sword, Question2 _q, Enemy cast1, Exclamation _ex) {//with teleport		
		if (_p.getPosition().getRow() == _m.getPosition().getRow()
				&& _p.getPosition().getCol() == _m.getPosition().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BOWSER JR.!");
				_m.killMinotaur();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BOWSER JR. killed you!");
				restart();
			}
		}
		if (_p.getPosition().getRow() == cast1.getCactus1().getRow() 
				&& _p.getPosition().getCol() == cast1.getCactus1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed Bob-Omb!");
				cast1.killCactus1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - Bob-Omb killed you!");
				restart();		}
	}
		if (_ex.getPosition().getRow() == _p.getPosition().getRow() 
				&& _ex.getPosition().getCol() == _p.getPosition().getCol()) {
			_ex.dead();
			_ex.setFalse1();
			setExTrue();
			Window.msg("You have triggered the block! In effect, you restored this world back to its high status of LIGHT & SHINE!");
	}
		if (_p.getPosition().getRow() == _r1.getPosition1().getRow() 
				&& _p.getPosition().getCol() == _r1.getPosition1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed KING BOO!");
				_r1.killRat1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - KING BOO killed you!");
				restart();
		}
	}
		
		if (_p.getPosition().getRow() == _r2.getPosition2().getRow() 
				&& _p.getPosition().getCol() == _r2.getPosition2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BOO!");
				_r2.killRat2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BOO killed you!");
				restart();
			}
		}
		
		if (_p.getPosition().getRow() == _r3.getPosition3().getRow() 
				&& _p.getPosition().getCol() == _r3.getPosition3().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BOO!");
				_r3.killRat3();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BOO killed you!");
				restart();
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(_p.getPosition().getRow() == _z.getEnd().getRow() && _p.getPosition().getCol() == _z.getEnd().getCol())
		{
			_p.killPlayer();
			Window.msg("Mario made it to the pipe!");	
			cnt++;
			Overseer4.setLevel4False();
			levelDone();
			
		}
		if(_p.getPosition().getRow() == _sword.getPosition().getRow()
				&& _p.getPosition().getCol() == _sword.getPosition().getCol()) {
			Window.msg("You obtained LINK's MASTER SWORD!");
			Window.msg2("By obtaining the MASTER SWORD, Mario has temporarily transformed into LINK!", lnksword);
			link = true;
			_p.hasSword(_sword);
		}
		
		if (null != _q.getQuestPos1() &&_p.getPosition().getRow() == _q.getQuestPos1().getRow() && _p.getPosition().getCol() == _q.getQuestPos1().getCol()) {
            if(_q.randomQuestion1()) { 
            	cnt+=20;
            	_q.killQues(1);
            }else{
            	cnt-=15;
            	 _q.killQues(1);
            }
        }
        if (null != _q.getQuestPos2() &&_p.getPosition().getRow() == _q.getQuestPos2().getRow() && _p.getPosition().getCol() == _q.getQuestPos2().getCol()) {
            if(_q.randomQuestion2()) {
            	cnt+=20;
                 	_q.killQues(2);
                 }else{
                	 cnt-=15;
                 	 _q.killQues(2);
                 }
            }
        if (null != _q.getQuestPos3() &&_p.getPosition().getRow() == _q.getQuestPos3().getRow() && _p.getPosition().getCol() == _q.getQuestPos3().getCol()) {
        	if(_q.randomQuestion3()) {
        		cnt+=20;
                	_q.killQues(3);
                }else{
                	cnt-=15;
                	 _q.killQues(3);
                }
           }
        if (null != _q.getQuestPos4() &&_p.getPosition().getRow() == _q.getQuestPos4().getRow() && _p.getPosition().getCol() == _q.getQuestPos4().getCol()) {
        	 if(_q.randomQuestion4()) {
        		  cnt += 20;
             	_q.killQues(4);
             }
             else {
            	  cnt -= 15;
             	_q.killQues(4);
             }   
        }
        if (null != _q.getQuestPos5() &&_p.getPosition().getRow() == _q.getQuestPos5().getRow() && _p.getPosition().getCol() == _q.getQuestPos5().getCol()) {
            if(_q.randomQuestion5()) {
            	cnt += 20;
            	_q.killQues(5);
            }
            else {
            	cnt -= 15;
            	_q.killQues(5);
            }
        }
	}

	//Handles the same thing as gamecheck1 but only if player comes in contact with enemies or questions
	public void gamecheck2(Player _p, Minotaur _m, Rat _r1, Rat _r2, Rat _r3, Sword _sword, Question2 _q, Enemy cast1, Exclamation _ex) {
		if (_p.getPosition().getRow() == _m.getPosition().getRow()
				&& _p.getPosition().getCol() == _m.getPosition().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BOWSER JR.!");
				_m.killMinotaur();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BOWSER JR. killed you!");
				restart();
			}
		}
		if (_p.getPosition().getRow() == cast1.getCactus1().getRow() 
				&& _p.getPosition().getCol() == cast1.getCactus1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed Bob-Omb!");
				cast1.killCactus1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - Bob-Omb killed you!");
				restart();		}
	}
		if (_p.getPosition().getRow() == _r1.getPosition1().getRow() 
				&& _p.getPosition().getCol() == _r1.getPosition1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed KING BOO!");
				_r1.killRat1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - KING BOO killed you!");
				restart();
		}
	}
		
		if (_p.getPosition().getRow() == _r2.getPosition2().getRow() 
				&& _p.getPosition().getCol() == _r2.getPosition2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BOO!");
				_r2.killRat2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BOO killed you!");
				restart();
			}
		}
		
		if (_p.getPosition().getRow() == _r3.getPosition3().getRow() 
				&& _p.getPosition().getCol() == _r3.getPosition3().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BOO!");
				_r3.killRat3();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BOO killed you!");
				restart();
			}
		}
		if (null != _q.getQuestPos1() &&_p.getPosition().getRow() == _q.getQuestPos1().getRow() && _p.getPosition().getCol() == _q.getQuestPos1().getCol()) {
            if(_q.randomQuestion1()) { 
            	cnt+=20;
            	_q.killQues(1);
            }else{
            	cnt-=15;
            	 _q.killQues(1);
            }
        }
		if (null != _q.getQuestPos1() &&_p.getPosition().getRow() == _q.getQuestPos1().getRow() && _p.getPosition().getCol() == _q.getQuestPos1().getCol()) {
            if(_q.randomQuestion1()) { 
            	cnt+=20;
            	_q.killQues(1);
            }else{
            	cnt-=15;
            	 _q.killQues(1);
            }
        }
        if (null != _q.getQuestPos2() &&_p.getPosition().getRow() == _q.getQuestPos2().getRow() && _p.getPosition().getCol() == _q.getQuestPos2().getCol()) {
            if(_q.randomQuestion2()) {
            	cnt+=20;
                 	_q.killQues(2);
                 }else{
                	 cnt-=15;
                 	 _q.killQues(2);
                 }
            }
        if (null != _q.getQuestPos3() &&_p.getPosition().getRow() == _q.getQuestPos3().getRow() && _p.getPosition().getCol() == _q.getQuestPos3().getCol()) {
        	if(_q.randomQuestion3()) {
        		cnt+=20;
                	_q.killQues(3);
                }else{
                		cnt-=15;
                	 _q.killQues(3);
                }
           }
        if (null != _q.getQuestPos4() &&_p.getPosition().getRow() == _q.getQuestPos4().getRow() && _p.getPosition().getCol() == _q.getQuestPos4().getCol()) {
        	 if(_q.randomQuestion4()) {
        		  cnt += 20;
             	_q.killQues(4);
             }
             else {
            	 cnt -= 15;
             	_q.killQues(4);
             }   
        }
        if (null != _q.getQuestPos5() &&_p.getPosition().getRow() == _q.getQuestPos5().getRow() && _p.getPosition().getCol() == _q.getQuestPos5().getCol()) {
            if(_q.randomQuestion5()) {
            	cnt += 20;
            	_q.killQues(5);
            }
            else {
            	cnt -= 15;
            	_q.killQues(5);
            }
        }
	}
	
	//Transitions the player from World 4 to World 5
	public void levelDone() {
		Overseer5 o = new Overseer5();
		Window.msg2("You reached the exit! Now entering World 5!"
				+ "\nCAUTION: Mario only has 50 turns and gains 30 turns or loses 25 turns from Question Blocks!", world5);
		Overseer5.STAGE5();
	}
}
