# Risk Calculator Application

We have certain tables- <br/>
1.	Company_Risk_Score (Containing all the company names with dimensions name and its scores)<br/>
2.	Risk Dimension (Containing all the above table’s dimensions weights)<br/>
Note- sum of all individual weight must be equal to 100%.<br/>
3.	risk_calc_logic (Containing all the formulas)
4.	Risk_Score_Level (Provide level to every company <br/>dimension based on score column, in which range their individual score lies)
Individual risk scores represent a risk level on a scale of 1 to 100, where 1 is the highest risk and 100 is the least risk.<br/>  
5.	Score_cap (for calculating total_risk_capped_score which we require in one of our formula)<br/>
6.	Finally, output (which stores the result of every formula, for every company)<br/>


# Setup Guide 
  
## Backend 
1. Open the project in any Java IDE<br/>
2. Run the project as spring boot project.<br/>
3. Open URL "https://localhost:8081".<br/>
4. Port can be changed in application.properties file.<br/>
  
## Frontend
1. Run npm install to install npm modules.<br/>
2. Run ng serve for a dev server. Navigate to http://localhost:4200/. The app will automatically reload if you change any of the source files.


