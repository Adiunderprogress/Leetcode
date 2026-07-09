import pandas as pd

def concatenateTables(df1: pd.DataFrame, df2: pd.DataFrame) -> pd.DataFrame:
    # Concatenate df1 and df2 vertically
    return pd.concat([df1, df2], axis=0)