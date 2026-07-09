import pandas as pd

def fillMissingValues(products: pd.DataFrame) -> pd.DataFrame:
    # Fill the missing values in the 'quantity' column with 0
    products['quantity'] = products['quantity'].fillna(0)
    
    return products