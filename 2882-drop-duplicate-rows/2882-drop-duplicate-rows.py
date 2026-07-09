import pandas as pd

def dropDuplicateEmails(customers: pd.DataFrame) -> pd.DataFrame:
    # Drop rows with duplicate emails, keeping only the first occurrence
    return customers.drop_duplicates(subset='email', keep='first')