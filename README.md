# Tic-Tac-Toe

Implemented using Java, the game is only for one player and he or she can attempt to defeat the AI.

## Implementation of AI

The algorithm used for the AI is Min-Max. Here is the pseudocode:

```
value(s)
    if s is terminal: return utility(s)
    if s is max_node: return max_value(s)
    if s is min_node: return min_value(s)

==================================================

max_value(s)
    m = neg_inf
    // action, a, s' = result(s, a)
    for a, s' in successors(s)
        v = value(s')
        m = max(m, v)
    return m

==================================================

min_value(s)
    m = pos_inf
    // action, a, s' = result(s, a)
    for a, s' in successors(s)
        v = value(s')
        m = min(m, v)
    return m
```

## Developers

* Arquilita, Jasper Ian Z.
* Canonizado, Carlos Miguel E.
