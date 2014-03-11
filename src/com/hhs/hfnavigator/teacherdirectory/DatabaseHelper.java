/*License

THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS 
OF THIS CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW. 
ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE 
OR COPYRIGHT LAW IS PROHIBITED.

Creative Commons License

This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License; 
you may not use this work except in compliance with the License.

You may obtain a copy of the License in the LICENSE file, 
or at http://creativecommons.org/licenses/by-nc-nd/3.0/deed.en_US

BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, 
YOU ACCEPT AND AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. 
TO THE EXTENT THIS LICENSE MAY BE CONSIDERED TO BE A CONTRACT, 
THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED HERE IN CONSIDERATION 
OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
*/

package com.hhs.hfnavigator.teacherdirectory;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "employee_directory";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/*
		 * Create the employee table and populate it with sample data.
		 * In step 6, we will move these hardcoded statements to an XML document.
		 */
		String sql = "CREATE TABLE IF NOT EXISTS employee (" +
						"_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						"firstName TEXT, " +
						"lastName TEXT, " +
						"title TEXT, " +
						"officePhone TEXT, " +
						"cellPhone TEXT, " +
						"email TEXT, " +
						"managerId INTEGER)";
		db.execSQL(sql);
		
		ContentValues values = new ContentValues();

		
		values.put("firstName", "Dr. Rory J. Manning");
		values.put("lastName", "");
		values.put("title", "Principal");
		values.put("email", "manningr@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Jami Goldstein Scherr");
		values.put("lastName", "");
		values.put("title", "Assistant Principal");
		values.put("email", "scherrj@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Robert Kelly");
		values.put("lastName", "");
		values.put("title", "Assistant Principal");
		values.put("email", "kellyr@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Christopher Agostino");
		values.put("lastName", "");
		values.put("title", "P.E. Teacher");
		values.put("email", "agostinoc@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Kristen Akbar");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email", "akbark@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Jennifer Ambrosio");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "ambrosioj@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Vincent Ambrosio");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "ambrosiov@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Marco Antenucci");
		values.put("lastName", "");
		values.put("title", "Art Teacher");
		values.put("email", "antenuccim@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Regina Antretter");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email", "antretterr@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Clara Baltrusis");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email", "baltrusisc@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Denise Barattini");
		values.put("lastName", "");
		values.put("title", "Permanent Substitute Teacher");
		values.put("email", "barattinid@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Daniel Barret");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "BarretD@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Jessica Behzadi");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "Behzadij@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "David Bender");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "bendered@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Thomas Bennett");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email", "Bennett@harborfieldscsd.org");
		db.insert("employee", "lastName", values);		

		values.put("firstName", "Lisa Bergman");
		values.put("lastName", "");
		values.put("title", "Guidance Counselor");
		values.put("email", "bergmanl@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Allison Bilawsky");
		values.put("lastName", "");
		values.put("title", "Music Teacher");
		values.put("email", "ScillaA@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Jacqueline Black");
		values.put("lastName", "");
		values.put("title", "Math Teacher");
		values.put("email", "blackj@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Barbara Blumberg-Rath");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email", "blumbergb@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Theo Bobetski-Hafkin");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "hafkint@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Craig Butler");
		values.put("lastName", "");
		values.put("title", "LOTE teacher");
		values.put("email", "butlerc@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Michelle Carnaxide");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "carnaxidem@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Kimberly Carrol");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "carrollk@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Anna Cervini");
		values.put("lastName", "");
		values.put("title", "ESL Teacher");
		values.put("email", "cervinia@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Barbara Cohen");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "CohenB@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Irene Contopoulous");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "contopoulousi@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Damianos Contopoulous");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "contopoulousdamianos@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Kathleen Csogi");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "csogik@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Theresa Damm");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "dammt@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Maria D'Amore");
		values.put("lastName", "");
		values.put("title", "LOTE Teacher");
		values.put("email", "D'AmoreM@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Nicholas D'Anna");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "dannan@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Michelle DaSilva");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "DaSilvaM@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Margareta Demarest");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "demarestm@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Elizabeth Devaney");
		values.put("lastName", "");
		values.put("title", "Art Teacher");
		values.put("email", "DevaneyE@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Lisa Drake");
		values.put("lastName", "");
		values.put("title", "School Psychologist");
		values.put("email", "drakel@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Carolann Essig");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "essigc@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Mary Fagan");
		values.put("lastName", "");
		values.put("title", "Guidance Counselor");
		values.put("email", "FaganM@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Carol Fleishman");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "FleishmanC@harborfieldscsd.org");
		db.insert("employee", "lastName", values);


		values.put("firstName", "Brian Flynn");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "FlynnBr@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Courtney-Ann Frisenda");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "frisendac@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Laura Gargaro");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "gargarol@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Kristen Gavin");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "gavink@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Debra Gerhardt");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email", "GerhardtD@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Patricia Giordano");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email", "GiordanoP@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Brian Given");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "GivenB@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Therese Gold");
		values.put("lastName", "");
		values.put("title", "LOTE Teacher");
		values.put("email", "GoldT@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Tomas Gonazalez");
		values.put("lastName", "");
		values.put("title", "Social Worker");
		values.put("email", "GonzalezT@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Daniel Greening");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "greeningd@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Kristen Greening");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "greeningk@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Gina Grubbs");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "grubbsg@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Kristina Grupinski");
		values.put("lastName", "");
		values.put("title", "Permanent Substitute Teacher");
		values.put("email", "grupinskik@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Susan Hahn");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "hahns@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Jessica Hansen");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "hansenj@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Dennis Harmon");
		values.put("lastName", "");
		values.put("title", "P.E. Teacher");
		values.put("email", "harmond1@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Jennifer Harmon");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email", "harmonj@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Brian Harris");
		values.put("lastName", "");
		values.put("title", "School Psychologist");
		values.put("email", "HarrisB@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Charlene Hoffman");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email", "HoffmanC@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Maryann Hoffman");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email", "HoffmanM@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Noreen Holst");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email", "HolstN@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Stuart Horowitz");
		values.put("lastName", "");
		values.put("title", "Librarian");
		values.put("email", "HorowitzS@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "James Incorvaia");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "IncorvaiaJ@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Clare Jackson");
		values.put("lastName", "");
		values.put("title", "Music Teacher");
		values.put("email", "jacksonc@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Christopher Kahler");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "KahlerC@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Patricia Kalvar");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "KalvarP@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Troy Kandler");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "KandlerT@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Michael Khan");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "KhanM@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Susan Koenig");
		values.put("lastName", "");
		values.put("title", "School Social Worker");
		values.put("email", "koenigs@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "George Kouroutis");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "kouroutisg@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Jessica LaMantia");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "lamantiaj@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Paul Lasinski");
		values.put("lastName", "");
		values.put("title", "Health/Student Assistance Liason");
		values.put("email", "LasinskiP@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Randy Logerfo");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "logerfor@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Patricio Lopez");
		values.put("lastName", "");
		values.put("title", "LOTE Teacher");
		values.put("email", "LopezP@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Matthew Maffia");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email", "maffiam@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Aspasia Mally");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email", "MallyA@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Ellen Mangiamele");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "mangiamelee@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName","Lorraine Mantello");
		values.put("lastName", "");
		values.put("title", "Permanent Substitute Teacher");
		values.put("email", "mantellol@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Nicholas Maravell");
		values.put("lastName", "");
		values.put("title", "Art Teacher");
		values.put("email", "MaravellN@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Donna Marotta");
		values.put("lastName", "");
		values.put("title", "Special Education");
		values.put("email", "marottad@harborfieldscsd.org");
		db.insert("employee", "lastName" , values);

		values.put("firstName","Allison Matthews");
		values.put("lastName", "");
		values.put("title", "Business Teacher");
		values.put("email", "MatthewsA@harborfieldscsd.org");
		db.insert("employee", "lastName" , values);

		values.put("firstName","Stefania Mattio");
		values.put("lastName", "");
		values.put("title", "LOTE Teacher");
		values.put("email", "MattioS@harborfieldscsd.org");
		db.insert("employee", "lastName" , values);

		values.put("firstName","Jeri McCabe");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "McCabeJ@harborfieldscsd.org");
		db.insert("employee", "lastName" , values);

		values.put("firstName","James McCabe");
		values.put("lastName", "");
		values.put("title", "Guidance Counselor");
		values.put("email", "mccabej1@harborfieldscsd.org");
		db.insert("employee", "lastName" , values);
		
		values.put("firstName","Annie McClintock");
		values.put("lastName", "");	
		values.put("title", "Teaching Assistant");
		values.put("email", "mcclintocka@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName","McCoy Robert");	
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "mccoyr@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName","Barbara McCullagh");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "mccullaghb@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Kerri McGinty");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "mcgintyk@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Keith Mcinnes");
		values.put("lastName", "");	
		values.put("title", "Science Teacher");
		values.put("email", "mcinnesk@harborfieldscsd.com");
		db.insert("employee", "lastName", values);

		values.put("firstName","Sabrina Meehan");
		values.put("lastName", "");	
		values.put("title", "Guidance counselor");
		values.put("email", "meehans@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName","Lisa Michalek");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email","michalek@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Brigitte Ohlenschlaeger");
		values.put("lastName", "");
		values.put("title", "Teaching assistant");
		values.put("email","ohlenschlaegerb@harbofields.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Amy Pechar");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email","pechara@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Laura Pfaff");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email","pfaffl@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Michael Pinto");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email","pintom@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Michael Potter");
		values.put("lastName", "");
		values.put("title", "PE/Health Teacher");
		values.put("email","PotterM@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Bryan Proctor");
		values.put("lastName", "");
		values.put("title", "Physical Education Teacher");
		values.put("email","proctorb@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Georgia Psilakis");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email","PsilakisG@garborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Catherine Purcell");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email","purcellc@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Caryl Reda");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email","redac@harborfields.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Jamie Reinish");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email","reinishj@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Eugenia Ritter");
		values.put("lastName", "");
		values.put("title", "Art Teacher");
		values.put("email","RitterE@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","AnnMaria Romereo");
		values.put("lastName", "");
		values.put("title", "LOTE Teacher");
		values.put("email","romeoa@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Steven Rona");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email","ronais@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName","Jule Rosen");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email","RosenJ@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
	
				values.put("firstName", "Alissa Rosenberg");
		values.put("lastName", "");
		values.put("title", "Speech Teacher");
		values.put("email", "rosenberga@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Francis Rossi");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "RossiF@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Judith  Russell");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email", "RusselJ@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Timothy Russo");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "RussoT@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Gregory Sagistano");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "sagistanog@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Sylvia Sales");
		values.put("lastName", "");
		values.put("title", "Guidance Counselor");
		values.put("email", "SalesS@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Scott Schaeffer");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "schaeffers@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
	        values.put("firstName", "Suzanne Scheer-Legge");
		values.put("lastName", "");
		values.put("title", "PE Teacher");
		values.put("email", "LeggeS@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Patricia Schoonmaker");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "schoonmakerp@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Eileen Shields");
		values.put("lastName", "");
		values.put("title", "Art Teacher ");
		values.put("email", "ShieldsE@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Karen Short");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "ShortK@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Cynthia Stanfield");
		values.put("lastName", "");
		values.put("title", "Paraprofessional");
		values.put("email", "StanfieldC@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Kim Stebbins");
		values.put("lastName", "");
		values.put("title", "Speech Teacher");
		values.put("email", "stebbinsk@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "John Stone");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "StoneJ@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		
	        values.put("firstName", "Sharon Swailes");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "SwailesS@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "John Tampori");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "amporij@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Gregory Taylor");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "TaylorG@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Patricia Taylor");
		values.put("lastName", "");
		values.put("title", "Social Studies Teacher");
		values.put("email", "TaylorP@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Andrea Thomsen");
		values.put("lastName", "");
		values.put("title", "English Teacher");
		values.put("email", "thomsena@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Aimee Thomson");
		values.put("lastName", "");
		values.put("title", "Mathematics Teacher");
		values.put("email", "ThomsonAi@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Jenny Turzer");
		values.put("lastName", "");
		values.put("title", "Special Education Teacher");
		values.put("email", "turzerj@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

	        
	        values.put("firstName", "Dawn Vavoules");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "vavoulesd@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Joan E Verardo");
		values.put("lastName", "");
		values.put("title", "Nurse");
		values.put("email", "VerardoJ@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Kalliope Viegas");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "viegask@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
		
		values.put("firstName", "Rita Vita");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "VitaR@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Scott J Wallace");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "WallaceS@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Alan Walter");
		values.put("lastName", "");
		values.put("title", "Music Teacher");
		values.put("email", "waltera@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Victoria Williams");
		values.put("lastName", "");
		values.put("title", "Clerical");
		values.put("email", "williamsv@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

                values.put("firstName", "Karen Wills");
		values.put("lastName", "");
		values.put("title", "Teaching Assistant");
		values.put("email", "willsk@harborfieldscsd.org");
		db.insert("employee", "lastName", values);

		values.put("firstName", "Christopher Zenyuh");
		values.put("lastName", "");
		values.put("title", "Science Teacher");
		values.put("email", "ZenyuhC@harborfieldscsd.org");
		db.insert("employee", "lastName", values);
	

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS employees");
		onCreate(db);
	}
	
}
