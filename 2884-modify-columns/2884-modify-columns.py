import pandas as pd

def modifySalaryColumn(employees: pd.DataFrame) -> pd.DataFrame:
    # Multiply every value in the 'salary' column by 2
    employees['salary'] = employees['salary'] * 2
    
    return employees