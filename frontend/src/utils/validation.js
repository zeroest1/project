function isValidDate(year, month, day) {
    const date = new Date(year, month - 1, day);
    // Check if month and day are valid.
    return !(date.getFullYear() !== year || date.getMonth() + 1 !== month || date.getDate() !== day);

}

function isValidEstonianPersonalId(id) {
    if (!id || id.length !== 11 || !id.match(/^\d{11}$/)) {
        return false; // Checks length and that id contains only digits
    }

    // Extracting date elements from the ID
    const yearPrefix = { '1': 1800, '2': 1800, '3': 1900, '4': 1900, '5': 2000, '6': 2000 };
    const century = id[0];
    const year = yearPrefix[century] + parseInt(id.slice(1, 3));
    const month = parseInt(id.slice(3, 5));
    const day = parseInt(id.slice(5, 7));

    // Validate month and day
    if (!isValidDate(year, month, day)) {
        return false;
    }

    // Calculate the checksum using the "Modulus 11" method
    const weightsFirst = [1, 2, 3, 4, 5, 6, 7, 8, 9, 1];
    const weightsSecond = [3, 4, 5, 6, 7, 8, 9, 1, 2, 3];
    let sumFirst = 0;
    let sumSecond = 0;

    for (let i = 0; i < 10; i++) {
        sumFirst += parseInt(id[i]) * weightsFirst[i];
        sumSecond += parseInt(id[i]) * weightsSecond[i];
    }

    let checksum = sumFirst % 11;
    if (checksum === 10) {
        checksum = sumSecond % 11;
        if (checksum === 10) {
            checksum = 0;
        }
    }

    return checksum === parseInt(id[10]); // Compare checksum with the last digit
}

export { isValidEstonianPersonalId };