import pandas as pd

def renameColumns(students: pd.DataFrame) -> pd.DataFrame:
    # Map old column names to new column names using a dictionary
    return students.rename(columns={
        'id': 'student_id',
        'first': 'first_name',
        'last': 'last_name',
        'age': 'age_in_years'
    })