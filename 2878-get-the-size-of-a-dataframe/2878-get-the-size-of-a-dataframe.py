import pandas as pd

def getDataframeSize(players: pd.DataFrame) -> List[int]:
    # .shape returns a tuple (rows, columns), which we convert to a list
    return list(players.shape)