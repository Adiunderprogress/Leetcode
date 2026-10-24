class Solution:
    def countDaysTogether(self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str) -> int:
        days_in_months = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        
        def convert_to_day_of_year(date_str: str) -> int:
            month, day = map(int, date_str.split('-'))
            # Sum up days of previous months + current month's days
            return sum(days_in_months[:month - 1]) + day
        
        # Convert all 4 dates into day-of-the-year integers
        a_arrive = convert_to_day_of_year(arriveAlice)
        a_leave = convert_to_day_of_year(leaveAlice)
        b_arrive = convert_to_day_of_year(arriveBob)
        b_leave = convert_to_day_of_year(leaveBob)
        
        # Calculate overlap boundaries
        overlap_start = max(a_arrive, b_arrive)
        overlap_end = min(a_leave, b_leave)
        
        # If the start of overlap is before or on the end of overlap, calculate days
        if overlap_start <= overlap_end:
            return overlap_end - overlap_start + 1
        
        return 0