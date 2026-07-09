import pandas as pd

def createDataframe(student_data: List[List[int]]) -> pd.DataFrame:
    # Combine the 2D list with the target column headers
    return pd.DataFrame(student_data, columns=['student_id', 'age'])