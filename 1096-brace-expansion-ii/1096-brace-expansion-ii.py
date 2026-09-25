class Solution:
    def braceExpansionII(self, expression: str) -> list[str]:
        def evaluate(left: int, right: int) -> set[str]:
            if left > right:
                return set()
            if left == right:
                return {expression[left]}
            
            # Pass 1: Union Pass (Look for top-level commas)
            depth = 0
            unions = []
            prev = left
            
            for i in range(left, right + 1):
                char = expression[i]
                if char == '{':
                    depth += 1
                elif char == '}':
                    depth -= 1
                elif char == ',' and depth == 0:
                    unions.append(evaluate(prev, i - 1))
                    prev = i + 1
            
            # If top-level commas were found, return union of all segments
            if unions or prev > left:
                unions.append(evaluate(prev, right))
                res = set()
                for u in unions:
                    res |= u
                return res
            
            # Pass 2: Concatenation Pass (Cartesian Product of adjacent terms)
            res = {""}  # Multiplicative identity (empty string set)
            depth = 0
            prev = left
            
            for i in range(left, right + 1):
                char = expression[i]
                if char == '{':
                    if depth == 0:
                        prev = i + 1
                    depth += 1
                elif char == '}':
                    depth -= 1
                    if depth == 0:
                        # Recursively evaluate the sub-expression inside braces
                        current = evaluate(prev, i - 1)
                        # Cartesian product
                        res = {l + r for l in res for r in current}
                elif depth == 0:
                    # Single character at top-level
                    res = {l + char for l in res}
                    
            return res

        # Evaluate the whole expression and return sorted unique results
        return sorted(list(evaluate(0, len(expression) - 1)))