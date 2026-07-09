import pandas as pd

def selectFirstRows(employees: pd.DataFrame) -> pd.DataFrame:
    # Use .head(3) to return only the first 3 rows of the DataFrame
    return employees.head(3)
    