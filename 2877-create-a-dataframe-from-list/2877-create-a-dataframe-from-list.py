import pandas as pd

def createDataframe(student_data: List[List[int]]) -> pd.DataFrame:
    # Define the column names as specified in the problem
    column_names = ['student_id', 'age']
    
    # Create and return the DataFrame
    return pd.DataFrame(student_data, columns=column_names)