import pandas as pd

def createBonusColumn(employees: pd.DataFrame) -> pd.DataFrame:
    # Create the 'bonus' column by doubling the 'salary' column values
    employees['bonus'] = employees['salary'] * 2
    
    return employees