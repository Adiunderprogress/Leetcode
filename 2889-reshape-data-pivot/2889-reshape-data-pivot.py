import pandas as pd

def pivotTable(weather: pd.DataFrame) -> pd.DataFrame:
    # Pivot the table: rows = month, columns = city, values = temperature
    return weather.pivot(index='month', columns='city', values='temperature')