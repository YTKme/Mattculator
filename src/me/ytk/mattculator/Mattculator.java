package me.ytk.mattculator;

import java.math.BigDecimal;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class Mattculator extends Activity {

	// Constant
	private static final String BILL_SUBTOTAL = "BILL_SUBTOTAL";
	private static final String TIP = "TIP";
	private static final String TAX = "TAX";
	private static final String PEOPLE = "PEOPLE";
	private static final String BILL_AFTER_TAX = "BILL_AFTER_TAX";
	private static final String BILL_AFTER_TIP = "BILL_AFTER_TIP";
	private static final String BILL_AFTER_TIP_TAX = "BILL_AFTER_TIP_TAX";
	private static final String BILL_EVEN_SPLIT = "BILL_EVEN_SPLIT";
	private static final String TIP_TOTAL = "TIP_TOTAL";
	private static final String TIP_SPLIT = "TIP_SPLIT";

	// Global
	private BigDecimal billSubtotal;
	private BigDecimal tip;
	private BigDecimal tax;
	private Integer people;
	private BigDecimal billAfterTax;
	private BigDecimal billAfterTip;
	private BigDecimal billAfterTipTax;
	private BigDecimal billEvenSplit;
	private BigDecimal tipTotal;
	private BigDecimal tipSplit;

	EditText etBillSubtotal;
	EditText etTip;
	EditText etTax;
	EditText etPeople;
	EditText etBillAfterTax;
	EditText etBillAfterTip;
	EditText etBillAfterTipTax;
	EditText etBillEvenSplit;
	EditText etTipTotal;
	EditText etTipSplit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mattculator);

		// Determine if application is start state or resume state
		if (savedInstanceState == null) {
			// Start
			billSubtotal = new BigDecimal(0);
			tip = new BigDecimal(0.15);
			tax = new BigDecimal(0.0875);
			people = 1;
			billAfterTax = new BigDecimal(0);
			billAfterTip = new BigDecimal(0);
			billAfterTipTax = new BigDecimal(0);
			billEvenSplit = new BigDecimal(0);
			tipTotal = new BigDecimal(0);
			tipSplit = new BigDecimal(0);
		} else {
			// Resume
			billSubtotal = (BigDecimal) savedInstanceState.get(BILL_SUBTOTAL);
			tip = (BigDecimal) savedInstanceState.get(TIP);
			tax = (BigDecimal) savedInstanceState.get(TAX);
			people = savedInstanceState.getInt(PEOPLE);
			billAfterTax = (BigDecimal) savedInstanceState.get(BILL_AFTER_TAX);
			billAfterTip = (BigDecimal) savedInstanceState.get(BILL_AFTER_TIP);
			billAfterTipTax = (BigDecimal) savedInstanceState
					.get(BILL_AFTER_TIP_TAX);
			tipTotal = (BigDecimal) savedInstanceState.get(TIP_TOTAL);
			tipSplit = (BigDecimal) savedInstanceState.get(TIP_SPLIT);
		}

		etBillSubtotal = (EditText) findViewById(R.id.etBillSubtotal);
		etTip = (EditText) findViewById(R.id.etTip);
		etTax = (EditText) findViewById(R.id.etTax);
		etPeople = (EditText) findViewById(R.id.etPeople);
		etBillAfterTax = (EditText) findViewById(R.id.etBillAfterTax);
		etBillAfterTip = (EditText) findViewById(R.id.etBillAfterTip);
		etBillAfterTipTax = (EditText) findViewById(R.id.etBillAfterTipTax);
		etBillEvenSplit = (EditText) findViewById(R.id.etBillEvenSplit);
		etTipTotal = (EditText) findViewById(R.id.etTipTotal);
		etTipSplit = (EditText) findViewById(R.id.etTipSplit);

		// Bill Subtotal text change listener
		etBillSubtotal.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					billSubtotal = new BigDecimal(s.toString());
				} catch (NumberFormatException nfe) {
					billSubtotal = new BigDecimal(0);
				}

				// Update the bill information
				updateBill();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// NOT USED
			}

			@Override
			public void afterTextChanged(Editable s) {
				// NOT USED
			}
		});

		// Tip text change listener
		etTip.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					tip = new BigDecimal(s.toString());
				} catch (NumberFormatException nfe) {
					tip = new BigDecimal(0.15);
				}

				// Update the bill information
				updateBill();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// NOT USED
			}

			@Override
			public void afterTextChanged(Editable s) {
				// NOT USED
			}
		});

		// Tax text change listener
		etTax.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					tax = new BigDecimal(s.toString());
				} catch (NumberFormatException nfe) {
					tax = new BigDecimal(0.0875);
				}

				// Update the bill information
				updateBill();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// NOT USED
			}

			@Override
			public void afterTextChanged(Editable s) {
				// NOT USED
			}
		});

		// People text change listener
		etPeople.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				try {
					people = Integer.parseInt(s.toString());
				} catch (NumberFormatException nfe) {
					people = 1;
				}

				// Update the bill information
				updateBill();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// NOT USED
			}

			@Override
			public void afterTextChanged(Editable s) {
				// NOT USED
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mattculator, menu);
		return true;
	}

	/**
	 * Update the bill information
	 */
	private void updateBill() {
		try {
			billSubtotal = new BigDecimal(etBillSubtotal.getText().toString());
			tip = new BigDecimal(etTip.getText().toString());
			tax = new BigDecimal(etTax.getText().toString());
			people = Integer.parseInt(etPeople.getText().toString());
		} catch (NumberFormatException nfe) {
			billSubtotal = new BigDecimal(0);
			tip = new BigDecimal(0.15);
			tax = new BigDecimal(0.0875);
			people = 1;
		}

		// Calculate tip total
		tipTotal = billSubtotal.multiply(tip.divide(new BigDecimal(100)));

		// Calculate tip split
		tipSplit = tipTotal.divide(new BigDecimal(people));

		// Calculate bill after tax
		billAfterTax = billSubtotal.add(billSubtotal.multiply(tax
				.divide(new BigDecimal(100))));

		// Calculate bill after tip
		billAfterTip = billSubtotal.add(tipTotal);

		// Calculate bill after tip and tax
		billAfterTipTax = billSubtotal.add(
				billSubtotal.multiply(tax.divide(new BigDecimal(100)))).add(
				tipTotal);

		// Calculate bill even split
		billEvenSplit = billAfterTipTax.divide(new BigDecimal(people));

		// Display updated bill information
		etBillAfterTax.setText(String.format("%.02f", billAfterTax));
		etBillAfterTip.setText(String.format("%.02f", billAfterTip));
		etBillAfterTipTax.setText(String.format("%.02f", billAfterTipTax));
		etBillEvenSplit.setText(String.format("%.02f", billEvenSplit));
		etTipTotal.setText(String.format("%.02f", tipTotal));
		etTipSplit.setText(String.format("%.02f", tipSplit));
	}

}
