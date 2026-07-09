import pandas as pd

def selectData(students: pd.DataFrame) -> pd.DataFrame:
    # Filter for student_id == 101 and select 'name' and 'age' columns
    return students.loc[students['student_id'] == 101, ['name', 'age']]