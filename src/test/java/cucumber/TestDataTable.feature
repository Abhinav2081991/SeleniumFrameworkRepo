Feature: Test Datatable feature file

@test1
Scenario:

    Given I want to test Datatable with Item and Quantity Maps
        | Item   | Quantity |
        | Guitar | 2        |
        | Amps   | 3        |
@test2
Scenario:
    Given I want to test Datatable with Item and Quantity Lists
        |Guitar   |2      |
        |Amps     |3       |

@test3
Scenario:
    Given I want to use the list as datatable lists
    |One|
    |Two|
    |Three|