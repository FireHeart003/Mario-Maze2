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

public class StringMap3 extends JFrame{
	/*
	 * World 3
	 * Declare and initialize all private instance variables
	 */
	private static final long serialVersionUID = 1L;

	private JPanel _map = new JPanel(new GridLayout(21,21));
	
	private JLabel[][] Maze = new JLabel[21][21];
	
	private Maze _z;
	private static final String[] WORLDS = {"World 2", "World 3", "World 4", "World 5"};
	private static final String[] RESTART = {"Restart GAME!", "Exit"};
	private ImageIcon think =  new ImageIcon((getClass().getResource("think.png")));
	private Icon go = new ImageIcon(getClass().getResource("lets go.png"));
	private Icon pipe = new ImageIcon(getClass().getResource("pip.png"));
	private int cnt = 49;
	
	//+20,-15
	//everything 28x28
	//wall and player 20x20
	//Images used for GUI aspect of game
	private Icon player = new ImageIcon(getClass().getResource("mar.png"));
	private Icon floor = new ImageIcon(getClass().getResource("ice.png"));
	private Icon wall = new ImageIcon(getClass().getResource("ice wall.png"));
	private Icon minotaur = new ImageIcon(getClass().getResource("morton icon.png"));
	private Icon exit = new ImageIcon(getClass().getResource("pipe (1).png"));
	private Icon rat1 = new ImageIcon(getClass().getResource("ludwig icon.png"));
	private Icon rat2 = new ImageIcon(getClass().getResource("ham.png"));
	private Icon sword = new ImageIcon(getClass().getResource("link.png"));
	private Icon quest = new ImageIcon(getClass().getResource("quest.png"));
	private Icon gameOver = new ImageIcon(getClass().getResource("bows.png"));
	private ImageIcon world4 =  new ImageIcon((getClass().getResource("world4.png")));
	private ImageIcon mar =  new ImageIcon((getClass().getResource("mar.jpg")));
	private ImageIcon cact =  new ImageIcon((getClass().getResource("nospike.png")));
	private Icon home = new ImageIcon(getClass().getResource("mansion.png"));
	private Icon lnksword = new ImageIcon(getClass().getResource("sword.png"));
	boolean link = false;	
	private ImageIcon lnk =  new ImageIcon((getClass().getResource("link icon.png")));

	/*
	 * Transition scene used to transition from world 3 to world 4 after finishing this level
	 */
	public void home() {
		Window.msg2("This world used to be colorful like a rainbow, but now it has become the hub of Boos!"
				+ "\n Maybe you can help! "
				+ "BE CAREFUL: Boos can dissapear and reappear...you will need some light!", home);
	}
	
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
	
	//Asks player if they want to restart
	public void askRestart() {
		int x = Window.option1(RESTART, "What Now?", think);
		if(x==0) {
			Window.msg2("Awesome, another try to save Princess Peach!", go);
			OverseerRestart.setRestart();
			Overseer3.setLevel3False();
			Run.RESTART();
		}
		if(x==1) {
		Window.msg2("Sending Mario back to Mushroom Kingdom...", pipe);
			System.exit(0);
		}
	}
	
	//Getters
	public int getCnt() { return cnt; }
	public JPanel getMap(){	return _map; }

	//Updates all inputs and handles all movements in the game
	public JPanel updateMap(Player _p, Maze z, String userIn, Minotaur m, Rat r1, Rat r2, Sword s, Question2 _q, Cactus cact1, Cactus cact2) {
		_map = new JPanel(new GridLayout(21,21));
			
		if (userIn.equals("NORTH")) {
			updateCnt();
            if (z.getMaze()[_p.getPosition().getRow()-1][_p.getPosition().getCol()] == false) {
                _p.move(Direction.UP, z);
                move(_p, z, m, r1, r2, s, _q, cact1, cact2);
                gamecheck1(_p, z, m, r1, r2, s, _q, cact1, cact2);   
                cnt--;
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
                move(_p, z, m, r1, r2, s, _q, cact1, cact2);
                gamecheck1(_p, z, m, r1, r2, s, _q, cact1, cact2);  
                cnt--;
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
               move(_p, z, m, r1, r2, s, _q, cact1, cact2);
               gamecheck1(_p, z, m, r1, r2, s, _q, cact1, cact2);
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
                move(_p, z, m, r1, r2, s, _q, cact1, cact2);
                gamecheck1(_p, z, m, r1, r2, s, _q, cact1, cact2);
                cnt--;
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
				else if(r == cact1.getCactus1().getRow() &&
						c == cact1.getCactus1().getCol()) {
					Maze[r][c] = new JLabel(cact);
					_map.add(Maze[r][c]);
				}
				else if(r == cact2.getCactus2().getRow() &&
						c == cact2.getCactus2().getCol()) {
					Maze[r][c] = new JLabel(cact);
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
				else if(r == _q.getQuestPos6().getRow() &&
						c == _q.getQuestPos6().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				else if(r == _q.getQuestPos7().getRow() &&
						c == _q.getQuestPos7().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				else if(r == _q.getQuestPos8().getRow() &&
						c == _q.getQuestPos8().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				else if(r == _q.getQuestPos9().getRow() &&
						c == _q.getQuestPos9().getCol()) {
					Maze[r][c] = new JLabel(quest);
					_map.add(Maze[r][c]);	
				}
				
				else if(r == z.getEnd().getRow() &&
						c == z.getEnd().getCol()) {
					Maze[r][c] = new JLabel(exit);
					_map.add(Maze[r][c]);	
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
	public void move(Player _p, Maze _z, Minotaur _m, Rat _r1, Rat _r2, Sword _sword, Question2 _q, Cactus cact1, Cactus cact2) {
		gamecheck2(_p,_m,_r1, _r2, _sword, _q, cact1, cact2);
		if(_r1.getIsAlive1()) {
			_r1.move1(_z, _p);
		}
		if(_r2.getIsAlive2()) {
			_r2.move2(_z, _p);
		}
		if(_m.getIsAlive()) {
			_m.move(_z, _p);
		}
		if(cact1.getIsAlive1()) {
			cact1.move1(_z);
		}
		if(cact2.getIsAlive1()) {
			cact2.move2(_z);
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
	public void gamecheck1(Player _p, Maze _z, Minotaur _m, Rat _r1, Rat _r2, Sword _sword, Question2 _q, Cactus cast1, Cactus cast2) {//with teleport		
		if (_p.getPosition().getRow() == _m.getPosition().getRow()
				&& _p.getPosition().getCol() == _m.getPosition().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed MORTON!");
				_m.killMinotaur();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - MORTON killed you!");
				restart();
			}
		}
		
		if (_p.getPosition().getRow() == _r1.getPosition1().getRow() 
				&& _p.getPosition().getCol() == _r1.getPosition1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed LUDWIG!");
				_r1.killRat1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - LUWDIG killed you!");
				restart();
		}
	}
		
		if (_p.getPosition().getRow() == _r2.getPosition2().getRow() 
				&& _p.getPosition().getCol() == _r2.getPosition2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed HAMMER BRO!");
				_r2.killRat2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - HAMMER BRO killed you!");
				restart();
			}
		}
		
		if (_p.getPosition().getRow() == cast1.getCactus1().getRow() 
				&& _p.getPosition().getCol() == cast1.getCactus1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BUZZY BEETLE!");
				cast1.killCactus1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BUZZY BEETLE killed you!");
				restart();		}
	}
		if (_p.getPosition().getRow() == cast2.getCactus2().getRow() 
				&& _p.getPosition().getCol() == cast2.getCactus2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BUZZY BEETLE!");
				cast2.killCactus2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BUZZY BEETLE killed you!");
				restart();		}
	}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(_p.getPosition().getRow() == _z.getEnd().getRow() && _p.getPosition().getCol() == _z.getEnd().getCol())
		{
			_p.killPlayer();
			Window.msg("Mario made it to the pipe!");	
			cnt++;
			Overseer3.setLevel3False();
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
        if (null != _q.getQuestPos6() &&_p.getPosition().getRow() == _q.getQuestPos6().getRow() && _p.getPosition().getCol() == _q.getQuestPos6().getCol()) {
       	 if(_q.randomQuestion6()) {
            	cnt += 20;
            	_q.killQues(6);
            }
            else {
            	cnt -= 15;
            	_q.killQues(6);
            }        }
       if (null != _q.getQuestPos7() &&_p.getPosition().getRow() == _q.getQuestPos7().getRow() && _p.getPosition().getCol() == _q.getQuestPos7().getCol()) {
       	 if(_q.randomQuestion7()) {
            	cnt += 20;
            	_q.killQues(7);
            }
            else {
            	cnt -= 15;
            	_q.killQues(7);
            }        }
       if (null != _q.getQuestPos8() &&_p.getPosition().getRow() == _q.getQuestPos8().getRow() && _p.getPosition().getCol() == _q.getQuestPos8().getCol()) {
       	 if(_q.randomQuestion8()) {
            	cnt += 20;
            	_q.killQues(8);
            }
            else {
            	cnt -= 15;
            	_q.killQues(8);
            }        }
       if (null != _q.getQuestPos9() &&_p.getPosition().getRow() == _q.getQuestPos9().getRow() && _p.getPosition().getCol() == _q.getQuestPos9().getCol()) {
       	 if(_q.randomQuestion9()) {
            	cnt += 20;
            	_q.killQues(9);
            }
            else {
            	cnt -= 15;
            	_q.killQues(9);
            }        }
	}
	
	//Handles the same thing as gamecheck1 but only if player comes in contact with enemies or questions
	public void gamecheck2(Player _p, Minotaur _m, Rat _r1, Rat _r2, Sword _sword, Question2 _q, Cactus cast1, Cactus cast2) {
		if (_p.getPosition().getRow() == _m.getPosition().getRow()
				&& _p.getPosition().getCol() == _m.getPosition().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed MORTON!");
				_m.killMinotaur();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - MORTON killed you!");
				restart();
			}
		}
		
		if (_p.getPosition().getRow() == _r1.getPosition1().getRow() 
				&& _p.getPosition().getCol() == _r1.getPosition1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed LUDWIG!");
				_r1.killRat1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - LUWDIG killed you!");
				restart();
		}
	}
		
		if (_p.getPosition().getRow() == _r2.getPosition2().getRow() 
				&& _p.getPosition().getCol() == _r2.getPosition2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed HAMMER BRO!");
				_r2.killRat2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - HAMMER BRO killed you!");
				restart();
			}
		}
		
		if (_p.getPosition().getRow() == cast1.getCactus1().getRow() 
				&& _p.getPosition().getCol() == cast1.getCactus1().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BUZZY BEETLE!");
				cast1.killCactus1();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BUZZY BEETLE killed you!");
				restart();		}
	}
		if (_p.getPosition().getRow() == cast2.getCactus2().getRow() 
				&& _p.getPosition().getCol() == cast2.getCactus2().getCol()) {
			if(_p.giveSword()) {
				Window.msg("You killed BUZZY BEETLE!");
				cast2.killCactus2();
				_p.hasSword1(_sword);
				link = false;
				Window.msg("Your sword has faded away, Mario is back!- Mario used it!");
			}
			else {
				_p.killPlayer();
				Window.msg("Mario died - BUZZY BEETLE killed you!");
				restart();		}
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
        if (null != _q.getQuestPos6() &&_p.getPosition().getRow() == _q.getQuestPos6().getRow() && _p.getPosition().getCol() == _q.getQuestPos6().getCol()) {
          	 if(_q.randomQuestion6()) {
               	cnt += 20;
               	_q.killQues(6);
               }
               else {
               	cnt -= 15;
               	_q.killQues(6);
               }        }
          if (null != _q.getQuestPos7() &&_p.getPosition().getRow() == _q.getQuestPos7().getRow() && _p.getPosition().getCol() == _q.getQuestPos7().getCol()) {
          	 if(_q.randomQuestion7()) {
               	cnt += 20;
               	_q.killQues(7);
               }
               else {
               	cnt -= 15;
               	_q.killQues(7);
               }        }
          if (null != _q.getQuestPos8() &&_p.getPosition().getRow() == _q.getQuestPos8().getRow() && _p.getPosition().getCol() == _q.getQuestPos8().getCol()) {
          	 if(_q.randomQuestion8()) {
               	cnt += 20;
               	_q.killQues(8);
               }
               else {
               	cnt -= 15;
               	_q.killQues(8);
               }        }
          if (null != _q.getQuestPos9() &&_p.getPosition().getRow() == _q.getQuestPos9().getRow() && _p.getPosition().getCol() == _q.getQuestPos9().getCol()) {
          	 if(_q.randomQuestion9()) {
               	cnt += 20;
               	_q.killQues(9);
               }
               else {
               	cnt -= 15;
               	_q.killQues(9);
               }        }
	}
	
	//Transitions the player from World 3 to World 4
	public void levelDone() {
		Overseer4 o = new Overseer4();
		Window.msg2("You reached the exit! Now entering World 4!"
				+ "\nCAUTION: Mario only has 70 turns and gains 20 turns or loses 15 turn from Question Blocks!", world4);
		home();
		Overseer4.STAGE4();
	}
}
