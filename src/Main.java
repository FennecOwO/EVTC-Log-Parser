import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

    	// Make directories if they don't exist
        String cwd = System.getProperty("user.dir");    	
    	new File(cwd + "/logs").mkdirs();
    	new File(cwd + "/graphs").mkdirs();
    	new File(cwd + "/tables").mkdirs();	
    	
	    // File loop
    	File dir = new File(cwd + "/logs");
    	File[] logs = dir.listFiles();
    	if (logs != null) {
    		for (File log : logs) {	
        		// Menu loop
    	    	try(Scanner scanner = new Scanner(System.in)) {
//    	    		System.out.println("EVTC Log Parser\n"
//    	    				+ "-------------------\n"
//    	    				+ "1. Final DPS\n"
//    	    				+ "2. Phase DPS\n"
//    	    				+ "3. Graph Total Damage (not implemented)\n"
//    	    				+ "4. Misc. Combat Stats\n"
//    	    				+ "5. Final Boons/Buffs\n"
//    	    				+ "6. Phase Boons/Buffs (not implemented)\n"
//    	    				+ "7. Text Dump Tables\n"
//    	    				+ "8. Quit\n");
//    	    		System.out.println("Choose an option (Enter to confirm): ");
    	    		// Choose an option
    	    		int choice = 5;
//    	    		int choice = scanner.nextInt();
    	    		
	                // Parse log
            		Parse parser = new Parse(log);
            		bossData b_data = parser.get_boss_data();
            		List<playerData> p_data = parser.get_player_data();
            		List<skillData> s_data = parser.get_skill_data();
            		List<combatData> c_data = parser.get_combat_data();
            		parser.fill_missing_data(b_data, p_data, s_data, c_data);
            		Statistics stats = new Statistics(b_data, p_data, s_data, c_data);
            		
            		// Interpret data based on choice
    		        switch (choice) {
		            	case 8:
		            		// Quit.
		            		System.exit(0);
		            		break;
		            	
    		            case 7:
    		                // Text Dump All Statistics
    		                break;
    		            case 1:
    		            case 2:
    		            case 3:
    		            case 4:
            		
		            		// Get damage logs
		            		stats.get_damage_logs();
		         
		            		// Final DPS *done*
    		            	if(choice == 1){
    		            		stats.get_final_dps();
    		            	}
    		            	// Phase DPS *done*
    		            	else if(choice == 2){
    		            		stats.get_phase_dps();
    		            	}
    		            	// Graph Total Damage **
    		            	else if(choice == 3){
    		            		
    		            	}
    		            	// Combat Statistics *WIP*
    		            	else if(choice == 4){
    		            		stats.get_combat_stats();
    		            	}
    		                break;         
    		            case 5:
    		            case 6:

    		        	    // Boon list
    		        		List<String> boon_list = new ArrayList<String>();
//    		        		boon_list.add("Might");
//    		        		boon_list.add("Fury");
    		        		boon_list.add("Quickness");
//    		        		boon_list.add("Protection");
//    		        		boon_list.add("Alacrity");
//    		        		
//    		        		boon_list.add("Grace of the Land");
//    		        		boon_list.add("Spotter");
//    		        		boon_list.add("Spirit of Frost");
//    		        		boon_list.add("Glyph of Empowerment");
//    		        		
//    		        		boon_list.add("Empower Allies");
//    		        		boon_list.add("Banner of Strength");
//    		        		boon_list.add("Banner of Discipline");
    		        		
    		        		// Get boon_logs
    		            	stats.get_boon_logs(boon_list);
    		            	if(choice == 5){
    		            		stats.get_final_boons(boon_list);
    		            	}
    		            	else if(choice == 6){
    		            		
    		            	}
    		            	break;
    		            default:
    		                System.out.println("Nope. Try again.\n");
    		        }	
    	      	}			
    		}
    	} else {
    		System.exit(0);
		}

	}

}
    
