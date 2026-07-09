import pandas as pd

def dropMissingData(students: pd.DataFrame) -> pd.DataFrame:
    # Drop rows where the 'name' column has missing values
    return students.dropna(subset=['name'])