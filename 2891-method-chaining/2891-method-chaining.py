import pandas as pd

def findHeavyAnimals(animals: pd.DataFrame) -> pd.DataFrame:
    # Filter rows, sort by weight descending, and select only the 'name' column
    return animals[animals['weight'] > 100].sort_values(by='weight', ascending=False)[['name']]