ALTER TABLE education
ADD CONSTRAINT unique_company_entry_year UNIQUE (company, entry_year);